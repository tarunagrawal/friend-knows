package com.system.you.review.web.controller.helper;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.you.review.core.service.MailService;
import com.system.you.review.item.bean.helper.impl.ReviewBeanHelper;
import com.system.you.review.item.bean.helper.impl.ReviewerBeanHelper;
import com.system.you.review.request.bean.Review;
import com.system.you.review.request.bean.Reviewer;
import com.system.you.review.request.service.ReviewService;
import com.system.you.review.request.service.ReviewerService;
import com.system.you.review.web.beans.form.ReviewFormBean;
import com.system.you.review.web.beans.response.RequestContext;
import com.system.you.review.web.beans.view.ReviewViewBean;
import com.system.you.review.web.beans.view.ReviewerViewBean;

@Service
public class AddReviewHelper extends ControllerHelper {

	public RequestContext<ReviewFormBean, ReviewerViewBean> submit(
			ReviewFormBean formBean) {
		RequestContext<ReviewFormBean, ReviewerViewBean> respBean = new RequestContext<ReviewFormBean, ReviewerViewBean>(
				formBean);
		try {
			validate(formBean, respBean);
			if (!respBean.containsMessage()) {
				Reviewer reviewerRecord = reviewerService.getReviewer(
						formBean.getReviewerRequestId(), true);
				if (reviewerRecord != null) {
					Review review = reviewService.addReview(formBean,
							reviewerRecord);
					if (review != null) {
						Reviewer reviewer = reviewerService.getReviewer(review
								.getReviewerRequestId());
						// make a cleanup for current http request.
						// makeCleanup(review, reviewer);
						respBean.setViewBean(reviewerBeanHelper
								.dataToView(reviewer));
						mailService.sendMessage(review, reviewer);
					} else {
						addSystemErrorMessage(respBean);
					}
				} else {
					addSystemErrorMessage(respBean);
				}
			}
		} catch (Exception ex) {
			LOGGER.error("exception occured while submitting review", ex);
			addSystemErrorMessage(respBean);
		}

		return respBean;
	}

	public RequestContext<ReviewFormBean, ReviewViewBean> submitIndependentReview(
			String category, String itemId, ReviewFormBean formBean) {
		RequestContext<ReviewFormBean, ReviewViewBean> respBean = new RequestContext<ReviewFormBean, ReviewViewBean>(
				formBean);
		try {
			validate(formBean, respBean);
			if (!respBean.containsMessage()) {
				Review review = reviewService.addIndependentReview(formBean,
						category, itemId);
				if (review != null) {
					ReviewViewBean viewBean = reviewBeanHelper.dbToView(review);
					respBean.setViewBean(viewBean);
				} else {
					addSystemErrorMessage(respBean);
				}
			}
		} catch (Exception ex) {
			LOGGER.error("exception occured while submitting review", ex);
			addSystemErrorMessage(respBean);
		}
		return respBean;
	}

	private void makeCleanup(Review review, Reviewer reviewer) {
		Set<Review> reviews = reviewer.getReviews();
		if (reviews != null) {
			reviews.add(review);
		} else {
			reviews = new HashSet<Review>();
			reviews.add(review);
			reviewer.setReviews(reviews);
		}
	}

	@Autowired
	private MailService mailService;

	@Autowired
	private ReviewService reviewService;

	@Autowired
	private ReviewerService reviewerService;

	@Autowired
	private ReviewerBeanHelper reviewerBeanHelper;

	@Autowired
	private ReviewBeanHelper reviewBeanHelper;

	private static Logger LOGGER = LoggerFactory
			.getLogger(AddReviewHelper.class);
}
