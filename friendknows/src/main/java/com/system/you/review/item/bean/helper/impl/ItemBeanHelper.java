package com.system.you.review.item.bean.helper.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.you.review.core.PopularTags;
import com.system.you.review.core.WeightedTag;
import com.system.you.review.core.service.TagService;
import com.system.you.review.item.bean.Item;
import com.system.you.review.item.bean.helper.BeanHelper;
import com.system.you.review.user.bean.ReviewUser;
import com.system.you.review.web.beans.view.ItemViewBean;
import com.system.you.review.web.beans.view.TagViewBean;
import com.system.you.review.web.domain.impl.SessionUtils;

@Service
public class ItemBeanHelper extends BeanHelper {

	public Item formToData(String categoryId, String id) {
		Item item = new Item();
		String description = null;
		if (StringUtils.isNotBlank(id)) {
			if (id.startsWith("new:")) {
				String[] values = id.split(":");
				description = values[1];
				id = null;
			}
		}
		item.setId(id);
		item.setDescription(StringUtils.upperCase(description));
		item.setCreateDateTime(current());
		item.setUpdateDateTime(current());
		if (StringUtils.isBlank(id)) {
			item.setCategory(categoryBeanHelper.formToData(categoryId));
		}
		return item;
	}

	public ItemViewBean dataToView(Item dbBean) {
		checkNulls(dbBean);
		ItemViewBean viewBean = new ItemViewBean();
		viewBean.setDescription(StringUtils.upperCase(dbBean.getDescription()));
		viewBean.setId(dbBean.getId());
		PopularTags popularTags = ratingService.getPopularTag(dbBean.getId());
		List<TagViewBean> publicTags = getTagViewBeans(popularTags);
		viewBean.setRating(publicTags);
		List<ReviewUser> connectedFriends = SessionUtils.getRequestor()
				.getConnectedFriends();
		viewBean.setConnectedRating(getTagViewBeans(ratingService
				.getPopularConnectedTags(dbBean.getId(), connectedFriends)));
		return viewBean;
	}

	private List<TagViewBean> getTagViewBeans(PopularTags popularTags) {
		WeightedTag[] weightedTags = popularTags.getPopularTags();
		List<TagViewBean> tagViewBeans = new ArrayList<TagViewBean>();
		for (WeightedTag weightedTag : weightedTags) {
			if (weightedTag != null) {
				TagViewBean tagViewBean = new TagViewBean();
				tagViewBean.setTagName(weightedTag.getTag().getViewName());
				tagViewBean.setCount(weightedTag.getCount());
				tagViewBeans.add(tagViewBean);
			}
		}
		return tagViewBeans;
	}

	@Autowired
	private CategoryBeanHelper categoryBeanHelper;

	@Autowired
	private TagService ratingService;
}
