package com.system.you.review.core.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.you.review.core.ItemCache;
import com.system.you.review.core.PopularTags;
import com.system.you.review.core.TagWeights;
import com.system.you.review.core.UserInterest;
import com.system.you.review.core.cache.CacheEvent;
import com.system.you.review.core.cache.CacheRegistry;
import com.system.you.review.core.lock.LockManager;
import com.system.you.review.core.lock.LockManagerFactory;
import com.system.you.review.core.service.TagService;
import com.system.you.review.request.bean.Review;
import com.system.you.review.request.service.ReviewService;
import com.system.you.review.user.bean.ReviewUser;
import com.system.you.review.user.service.ReviewUserService;
import com.system.you.review.web.domain.impl.SessionUtils;

@Service
public class TagServiceImpl implements TagService {

	@PostConstruct
	public void postConstructor() {
		this.lockManager = lockManagerFactory
				.getLockManager(com.system.you.review.core.lock.Resource
						.getResource(TagService.class.getName()));
		registerCacheWithRegistry();
	}

	@Override
	public PopularTags getPopularTag(String itemId) {
		TagWeights tag = getOverall(itemId);
		return tag.getPopularTags();
	}

	@Override
	public PopularTags getPopularConnectedTags(String itemId,
			List<ReviewUser> friends) {
		ItemCache itemCache = (ItemCache) itemTagMap.get(itemId);
		if (itemCache == null) {
			itemCache = buildCache(itemId);
		}
		TagWeights connectedWeight = getConnectedTag(friends, itemCache);
		return connectedWeight.getPopularTags();
	}

	@Override
	public List<UserInterest> getInterestedFriends(String itemId,
			List<String> providerIds) {
		if (providerIds != null && StringUtils.isNotBlank(itemId)) {
			ItemCache itemCache = getCacheItem(itemId);
			Map<String, TagWeights> connectedTags = itemCache
					.getConnectedTags(providerIds);
			List<UserInterest> friendsInterest = new ArrayList<UserInterest>();
			for (Map.Entry<String, TagWeights> entry : connectedTags.entrySet()) {
				String providerId = entry.getKey();
				TagWeights tagWeight = entry.getValue();
				UserInterest userInterest = new UserInterest();
				userInterest.setItemId(itemId);
				userInterest.setTags(tagWeight.getPopularTags());
				userInterest.setToalReviews(tagWeight.getTotal());
				userInterest.setUser(userService.getByProviderId(providerId));
				friendsInterest.add(userInterest);
			}
			return friendsInterest;
		}
		return new ArrayList<UserInterest>();
	}

	private ItemCache getCacheItem(String itemId) {
		ItemCache itemCache = (ItemCache) itemTagMap.get(itemId);
		if (itemCache == null) {
			itemCache = buildCache(itemId);
		}
		return itemCache;
	}

	@Override
	public void triggerChange(CacheEvent event) {
		Object changedBean = event.getBean();
		if (isOfInterest(changedBean)) {
			String itemId = getCacheKey(changedBean);
			invalidate(itemId);
		}
	}

	@Override
	public PopularTags getConnectedTags(String itemId) {
		return getPopularConnectedTags(itemId, SessionUtils.getRequestor()
				.getConnectedFriends());
	}

	private String getCacheKey(Object changedBean) {
		Review review = (Review) changedBean;
		String itemId = review.getItem().getId();
		return itemId;
	}

	private boolean isOfInterest(Object changedBean) {
		return changedBean instanceof Review;
	}

	private void invalidate(String itemId) {
		boolean gotLock = false;
		try {
			gotLock = lockManager.tryLock(itemId);
			itemTagMap.remove(itemId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (gotLock) {
				lockManager.unlock(itemId);
			}
		}
	}

	@Override
	public PopularTags getPopularReviewTag(Review review) {
		List<Review> reviews = new ArrayList<Review>();
		reviews.add(review);
		ItemCache itemCache = new ItemCache(review.getItem().getId(), reviews);
		return itemCache.getTotalTags().getPopularTags();
	}

	@Override
	public void registerCacheWithRegistry() {
		cacheRegistry.registerCacheService(this);
	}

	private TagWeights getOverall(String itemId) {
		ItemCache itemCache = null;
		itemCache = getCacheItem(itemId);
		return itemCache.getTotalTags();
	}

	private ItemCache buildCache(String itemId) {
		ItemCache itemCache = null;
		boolean gotLock = false;
		try {
			gotLock = lockManager.tryLock(itemId);
			if (gotLock) {
				// check again if at all any other thread has loaded the item.
				itemCache = (ItemCache) itemTagMap.get(itemId);
				if (itemCache == null) {
					// build the cache
					build(itemId);
					// after build read from memory
					itemCache = (ItemCache) itemTagMap.get(itemId);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (gotLock) {
				lockManager.unlock(itemId);
			}
		}
		// In any case cache element was not initialized. Return dummy
		if (itemCache == null) {
			itemCache = new ItemCache("Dummy", new ArrayList<Review>());
		}
		return itemCache;
	}

	@SuppressWarnings("unchecked")
	private void build(String itemId) {
		List<Review> reviews = getReviews(itemId);
		if (reviews != null && reviews.size() > 0) {
			ItemCache itemCache = new ItemCache(itemId, reviews);
			itemTagMap.put(itemId, itemCache);
		}
	}

	private List<Review> getReviews(String itemId) {
		List<Review> reviews = null;
		try {
			reviews = reviewService.getReviewsForItem(itemId);
		} catch (Exception ex) {
			logger.error("error occured while retriving reviews for item "
					+ itemId);
		}
		return reviews;
	}

	private TagWeights getConnectedTag(List<ReviewUser> friends,
			ItemCache itemCache) {
		List<String> providerIds = getProviderIds(friends);
		Map<String, TagWeights> connectedTags = itemCache
				.getConnectedTags(providerIds);
		TagWeights[] tagWeights = new TagWeights[connectedTags.size()];
		int i = 0;
		for (Entry<String, TagWeights> entry : connectedTags.entrySet()) {
			tagWeights[i++] = entry.getValue();
		}
		TagWeights connectedWeight = new TagWeights(tagWeights);
		return connectedWeight;
	}

	private List<String> getProviderIds(List<ReviewUser> friends) {
		return SessionUtils.getRequestor().getFriendsIds();
	}

	@Autowired
	private ReviewUserService userService;

	@Autowired
	private ReviewService reviewService;

	@Autowired
	private LockManagerFactory lockManagerFactory;

	@Autowired
	private CacheRegistry cacheRegistry;

	@SuppressWarnings("rawtypes")
	@Resource(name = "itemTagMap")
	private Map itemTagMap;

	private LockManager lockManager;

	private static Logger logger = LoggerFactory
			.getLogger(TagServiceImpl.class);

}
