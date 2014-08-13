package com.system.you.review.web.controller.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.you.review.core.service.exception.ServiceException;
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
import com.system.you.review.web.review.ReviewEditException;

@Service
public class EditReviewHelper extends ControllerHelper {

	public RequestContext<String, ReviewViewBean> editForm(String reviewId)
			throws ReviewEditException {
		RequestContext<String, ReviewViewBean> requestContext = new RequestContext<String, ReviewViewBean>(
				reviewId);
		try {
			ReviewViewBean viewBean = getViewBean(reviewId);
			if (viewBean != null) {
				requestContext.setViewBean(viewBean);
			} else {
				addSystemErrorMessage(requestContext);
			}
		} catch (Exception ex) {
			addSystemErrorMessage(requestContext);
		}
		return requestContext;
	}

	public RequestContext<ReviewFormBean, ReviewerViewBean> edit(
			ReviewFormBean formBean) throws ReviewEditException {
		String reviewId = formBean.getReviewerRequestId();
		RequestContext<ReviewFormBean, ReviewerViewBean> respBean = new RequestContext<ReviewFormBean, ReviewerViewBean>(
				formBean);
		try {
			validate(formBean, respBean);
			if (!respBean.containsMessage()) {
				Review review = reviewService.edit(reviewId, formBean);
				if (review != null) {
					respBean.setViewBean(getReviewerViewBean(review));
				} else {
					addSystemErrorMessage(respBean);
				}
			}
		} catch (Exception ex) {
			LOGGER.error("exception occurred while editing the review");
			addSystemErrorMessage(respBean);
		}
		return respBean;
	}

	private ReviewViewBean getViewBean(String reviewId) throws ServiceException {
		Review review = reviewService.get(reviewId);
		if (review != null) {
			return viewBean(review);
		}
		return null;
	}

	private ReviewViewBean viewBean(Review review) {
		return reviewBeanHelper.dbToView(review);
	}

	private ReviewerViewBean getReviewerViewBean(Review review)
			throws Exception {
		Reviewer reviewer = reviewerService.getReviewer(review
				.getReviewerRequestId());
		return reviewerBeanHelper.dataToView(reviewer);
	}

	@Autowired
	private ReviewService reviewService;

	@Autowired
	private ReviewerService reviewerService;

	@Autowired
	private ReviewerBeanHelper reviewerBeanHelper;

	@Autowired
	private ReviewBeanHelper reviewBeanHelper;

	private static Logger LOGGER = LoggerFactory
			.getLogger(EditReviewHelper.class);
}
