package com.system.you.review.item.bean.helper.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.you.review.core.PopularTags;
import com.system.you.review.core.WeightedTag;
import com.system.you.review.core.service.TagService;
import com.system.you.review.item.bean.helper.BeanHelper;
import com.system.you.review.request.bean.Review;
import com.system.you.review.web.beans.form.ReviewFormBean;
import com.system.you.review.web.beans.view.ItemViewBean;
import com.system.you.review.web.beans.view.ReviewViewBean;
import com.system.you.review.web.beans.view.TagViewBean;

@Service
public class ReviewBeanHelper extends BeanHelper {

	public Review formToDB(ReviewFormBean formBean) {
		checkNulls(formBean);
		Review dbBean = new Review();
		dbBean.setReviewDescription(formBean.getReviewDescription());
		Date now = new Date();
		dbBean.setCreateDateTime(now);
		dbBean.setUpdateDateTime(now);
		dbBean.setRating(formBean.getRating());
		dbBean.setVerified(Review.NOT_VERIFED);
		dbBean.setReviewer(currentUser());
		return dbBean;
	}

	public ReviewViewBean dbToView(Review dbBean, boolean full) {
		ReviewViewBean viewBean = dbToView(dbBean);
		if (full) {
			ItemViewBean itemViewBean = itemBeanHelper.dataToView(dbBean
					.getItem());
			viewBean.setItem(itemViewBean);
		}
		return viewBean;
	}

	public ReviewViewBean dbToView(Review dbBean) {
		checkNulls(dbBean);
		ReviewViewBean viewBean = new ReviewViewBean();
		viewBean.setId(dbBean.getReviewID());
		viewBean.setDescription(applyXSSFilter(dbBean.getReviewDescription()));
		viewBean.setDateTime(date(dbBean.getCreateDateTime()));
		viewBean.setRating(dbBean.getRating());
		viewBean.setReviewUser(userBeanHelper.dataToView(dbBean.getReviewer()));
		PopularTags popularTag = tagService.getPopularReviewTag(dbBean);
		WeightedTag[] weightedTags = popularTag.getPopularTags();
		if (weightedTags != null) {
			TagViewBean tagViewBean = new TagViewBean();
			WeightedTag weightedTag = weightedTags[0];
			tagViewBean.setTagName(weightedTag.getTag().getViewName());
			tagViewBean.setCount(weightedTag.getCount());
			viewBean.setTag(tagViewBean);
		}

		char verified = dbBean.getVerified();
		boolean agreed = (Review.VERIFED == verified);
		viewBean.setAgreed(agreed);
		return viewBean;
	}

	@Autowired
	private TagService tagService;

	@Autowired
	private UserBeanHelper userBeanHelper;

	@Autowired
	private ItemBeanHelper itemBeanHelper;
}
