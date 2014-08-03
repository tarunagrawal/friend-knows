package com.system.you.review.item.bean.helper.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.you.review.core.service.TagService;
import com.system.you.review.item.bean.helper.BeanHelper;
import com.system.you.review.request.bean.Review;
import com.system.you.review.web.beans.form.ReviewFormBean;
import com.system.you.review.web.beans.view.ReviewViewBean;

@Service
public class ReviewBeanHelper extends BeanHelper {

	public Review formToDB(ReviewFormBean formBean){
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
	
	public ReviewViewBean dbToView(Review dbBean){
		checkNulls(dbBean);
		ReviewViewBean viewBean = new ReviewViewBean();
		viewBean.setId(dbBean.getReviewID());
		viewBean.setDescription(dbBean.getReviewDescription());
		viewBean.setDateTime(date(dbBean.getCreateDateTime()));
		viewBean.setRating(dbBean.getRating());
		viewBean.setTag(tagService.getPopularReviewTag(dbBean));
		return viewBean;
	}
	
	@Autowired
	private TagService tagService;
}
