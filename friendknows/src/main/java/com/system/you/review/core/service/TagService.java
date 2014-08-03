package com.system.you.review.core.service;

import java.util.List;

import com.system.you.review.core.PopularTags;
import com.system.you.review.core.UserInterest;
import com.system.you.review.core.cache.CacheService;
import com.system.you.review.request.bean.Review;
import com.system.you.review.user.bean.ReviewUser;

public interface TagService extends CacheService {

	public PopularTags getPopularTag(String itemId);

	public PopularTags getConnectedTags(String itemId);

	public PopularTags getPopularConnectedTags(String itemId,
			List<ReviewUser> friendList);

	public PopularTags getPopularReviewTag(Review review);

	public List<UserInterest> getInterestedFriends(String itemId, List<String> providerIds);
}
