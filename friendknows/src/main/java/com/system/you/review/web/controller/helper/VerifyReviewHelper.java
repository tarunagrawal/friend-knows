package com.system.you.review.web.controller.helper;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.you.review.item.bean.helper.impl.ReviewerBeanHelper;
import com.system.you.review.request.bean.Review;
import com.system.you.review.request.bean.Reviewer;
import com.system.you.review.request.service.ReviewService;
import com.system.you.review.request.service.ReviewerService;
import com.system.you.review.web.beans.response.RequestContext;
import com.system.you.review.web.beans.view.ReviewerViewBean;
import com.system.you.review.web.beans.view.ViewBean;
import com.system.you.review.web.review.ReviewVerifyException;

@Service
public class VerifyReviewHelper extends ControllerHelper {

	public RequestContext<String, String> verify(String reviewId)
			throws ReviewVerifyException {
		RequestContext<String, String> requestContext = new RequestContext<String, String>(
				reviewId);
		try {
			validateInput(requestContext);
			if (!requestContext.containsMessage()) {
				if (reviewService.verify(reviewId) == null) {
					requestContext.addMessage("page",
							getMessage("review.verify.error", null));
				}
			}
		} catch (Exception ex) {
			addSystemErrorMessage(requestContext);
		}
		return requestContext;
	}

	public RequestContext<String[], ReviewerViewBean> copyReview(
			String reviewerId, String reviewId) {
		RequestContext<String[], ReviewerViewBean> requestContext = new RequestContext<String[], ReviewerViewBean>(
				new String[] { reviewerId, reviewId });
		try {
			validateCopyFormData(requestContext);
			if (!requestContext.containsMessage()) {
				Review review = reviewService.copyToReviewer(reviewId,
						reviewerId);
				if (review != null) {
					Reviewer reviewer = reviewerService.getReviewer(reviewerId);
					requestContext.setViewBean(reviewerBeanHelper
							.dataToView(reviewer));
				} else {
					addSystemErrorMessage(requestContext);
				}
			}
		} catch (Exception ex) {
			addSystemErrorMessage(requestContext);
		}
		return requestContext;
	}

	private void validateCopyFormData(RequestContext<String[], ?> requestContext) {
		String[] formBean = requestContext.getFormBean();
		if (StringUtils.isBlank(formBean[0])) {
			requestContext.addMessage("reviewer",
					getMessage("reviewer.id.field.missing", null));
		}
		if (StringUtils.isBlank(formBean[1])) {
			requestContext.addMessage("reviewId",
					getMessage("review.id.field.missing", null));
		}
	}

	private void validateInput(RequestContext<String, String> requestContext) {
		if (StringUtils.isBlank(requestContext.getFormBean())) {
			requestContext.addMessage("reviewId",
					getMessage("review.id.field.missing", null));
		}
	}

	@Autowired
	private ReviewService reviewService;

	@Autowired
	private ReviewerService reviewerService;

	@Autowired
	private ReviewerBeanHelper reviewerBeanHelper;

	private static Logger logger = LoggerFactory
			.getLogger(DeleteReviewHelper.class);
}
