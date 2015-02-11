package com.system.you.review.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.system.you.review.core.exception.UIException;
import com.system.you.review.web.UIExceptionFactory;
import com.system.you.review.web.beans.response.RequestContext;
import com.system.you.review.web.beans.view.ReviewerViewBean;
import com.system.you.review.web.controller.helper.RemoveReviewerHelper;
import com.system.you.review.web.controller.helper.VerifyReviewHelper;
import com.system.you.review.web.reviewer.exception.RemoveReviewerException;

@Controller
@RequestMapping(value = "/Reviewer/")
public class ReviewerController extends ControllerSupport {

	@RequestMapping(value = "{reviewerId}/Request/{requestId}/Reviewer/{fwdFeviewerId}/Remove")
	public ModelAndView removeReviewer(@PathVariable String reviewerId,
			@PathVariable String requestId, @PathVariable String fwdFeviewerId,
			Model model) throws UIException {
		RequestContext<String[], ReviewerViewBean> requestContext = removeReviewerHelper
				.removeForwaredReviewer(reviewerId, requestId, fwdFeviewerId);
		requestContext.setSuccessView("reviewerData");
		requestContext.setErrorView("error");
		requestContext.setModel(model);
		return handleResponse(requestContext, new UIExceptionFactory() {
			@Override
			public UIException create(final Model model, final String viewName) {
				return new RemoveReviewerException(model, viewName);
			}
		});
	}

	@RequestMapping(value = "{reviewerId}/Review/{reviewId}")
	public ModelAndView forwardBack(@PathVariable String reviewerId,
			@PathVariable String reviewId, Model model) throws UIException {
		RequestContext<String[], ReviewerViewBean> requestContext = verifyReviewerHelper
				.copyReview(reviewerId, reviewId);
		requestContext.setSuccessView("reviewerData");
		requestContext.setErrorView("error");
		requestContext.setModel(model);
		return handleResponse(requestContext, new UIExceptionFactory() {
			@Override
			public UIException create(final Model model, final String viewName) {
				return new RemoveReviewerException(model, viewName);
			}
		});
	}

	@Autowired
	private RemoveReviewerHelper removeReviewerHelper;

	@Autowired
	private VerifyReviewHelper verifyReviewerHelper;
}
