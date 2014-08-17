package com.system.you.review.web.controller;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.system.you.review.core.exception.UIException;
import com.system.you.review.web.UIExceptionFactory;
import com.system.you.review.web.beans.form.ReviewFormBean;
import com.system.you.review.web.beans.response.RequestContext;
import com.system.you.review.web.beans.view.ReviewViewBean;
import com.system.you.review.web.beans.view.ReviewerViewBean;
import com.system.you.review.web.controller.helper.AddReviewHelper;
import com.system.you.review.web.controller.helper.DeleteReviewHelper;
import com.system.you.review.web.controller.helper.EditReviewHelper;
import com.system.you.review.web.controller.helper.VerifyReviewHelper;
import com.system.you.review.web.review.ReviewCreateException;
import com.system.you.review.web.review.ReviewDeleteException;
import com.system.you.review.web.review.ReviewEditException;
import com.system.you.review.web.review.ReviewVerifyException;

@Controller
@RequestMapping(value = "/Request/{requestId}/Reviewer/{reviewerId}/Review")
public class ReviewController extends ControllerSupport {

	@RequestMapping(value = "/New")
	@ResponseStatus(value = HttpStatus.OK)
	public String addForm(@PathVariable String requestId,
			@PathVariable String reviewerId, Model model) {
		setRequestAndReviewerIds(requestId, reviewerId, model);
		return "addReview";
	}

	@RequestMapping(value = "/New/Submit")
	@ResponseStatus(value = HttpStatus.OK)
	public ModelAndView addSubmit(ReviewFormBean formBean, Model model)
			throws UIException {
		RequestContext<ReviewFormBean, ReviewerViewBean> requestContext = addReviewHelper
				.submit(formBean);
		requestContext.setSuccessView("reviewerData");
		requestContext.setErrorView("erorr");
		requestContext.setModel(model);
		model.addAttribute(FORM_BEAN, requestContext.getFormBean());
		return handleResponse(requestContext, new UIExceptionFactory() {
			@Override
			public UIException create(Model model, String viewName) {
				return new ReviewCreateException(model, viewName);
			}
		});
	}

	@RequestMapping(value = "/{reviewId}/Edit")
	@ResponseStatus(value = HttpStatus.OK)
	public ModelAndView editForm(@PathVariable String requestId,
			@PathVariable String reviewerId, @PathVariable String reviewId,
			Model model) throws UIException {
		String viewName = "editReview";
		RequestContext<String, ReviewViewBean> requestContext = editReviewHelper
				.editForm(reviewId);
		requestContext.setErrorView(viewName);
		requestContext.setSuccessView(viewName);
		requestContext.setModel(model);
		return handleResponse(requestContext, new UIExceptionFactory() {
			@Override
			public UIException create(Model model, String viewName) {
				return new ReviewEditException(model, viewName);
			}
		});
	}

	@RequestMapping(value = "/{reviewId}/Edit/Submit")
	@ResponseStatus(value = HttpStatus.OK)
	public ModelAndView edit(ReviewFormBean formBean, Model model)
			throws UIException {
		// Using reviewer Request Id as review id for edit purpose. Saves us a
		// formBean
		RequestContext<ReviewFormBean, ReviewerViewBean> requestContext = editReviewHelper
				.edit(formBean);
		String viewName = "reviewerData";
		requestContext.setErrorView("error");
		requestContext.setSuccessView(viewName);
		requestContext.setModel(model);
		return handleResponse(requestContext, new UIExceptionFactory() {
			@Override
			public UIException create(Model model, String viewName) {
				return new ReviewEditException(model, viewName);
			}
		});
	}

	@RequestMapping(value = "/{reviewId}/Remove")
	@ResponseStatus(value = HttpStatus.OK)
	public ModelAndView delete(@PathVariable String reviewId, Model model)
			throws UIException {
		RequestContext<String, ReviewerViewBean> requestContext = deleteReviewHelper
				.delete(reviewId);
		requestContext.setErrorView("error");
		requestContext.setSuccessView("reviewerData");
		requestContext.setModel(model);
		return handleResponse(requestContext, new UIExceptionFactory() {
			@Override
			public UIException create(Model model, String viewName) {
				return new ReviewDeleteException(model, viewName);
			}
		});
	}

	@RequestMapping(value = "{reviewId}/Agree")
	@ResponseStatus(value = HttpStatus.OK)
	public ModelAndView agree(@PathVariable String requestId,
			@PathVariable String reviewerId, @PathVariable String reviewId,
			Model model) throws UIException {
		RequestContext<String, String> requestContext = verifyReviewHelper
				.verify(requestId, reviewerId, reviewId);
		requestContext.setErrorView("error");
		requestContext.setSuccessView("agree");
		requestContext.setModel(model);
		return handleResponse(requestContext, new UIExceptionFactory() {
			@Override
			public UIException create(Model model, String viewName) {
				return new ReviewVerifyException(model, viewName);
			}
		});
	}

	@ExceptionHandler(value = { ReviewCreateException.class,
			ReviewEditException.class, ReviewDeleteException.class,
			ReviewVerifyException.class, UIException.class })
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ModelAndView handleUIException(UIException uiException) {
		String message = uiException.getMessage();
		Throwable cause = uiException.getCause();
		logger.error((StringUtils.isBlank(message)) ? "" : message, cause);
		return new ModelAndView(uiException.getViewName(), uiException
				.getModel().asMap());
	}

	private void setRequestAndReviewerIds(String requestId, String reviewerId,
			Model model) {
		model.addAttribute("requestId", requestId);
		model.addAttribute("reviewerId", reviewerId);
	}

	@Autowired
	private AddReviewHelper addReviewHelper;

	@Autowired
	private EditReviewHelper editReviewHelper;

	@Autowired
	private DeleteReviewHelper deleteReviewHelper;

	@Autowired
	private VerifyReviewHelper verifyReviewHelper;

	private static Logger logger = LoggerFactory
			.getLogger(ReviewController.class);
}
