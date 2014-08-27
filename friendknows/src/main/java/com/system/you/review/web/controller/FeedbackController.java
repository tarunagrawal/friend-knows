package com.system.you.review.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.system.you.review.core.exception.UIException;
import com.system.you.review.feedback.bean.FeedbackFormBean;
import com.system.you.review.feedback.bean.FeedbackViewBean;
import com.system.you.review.web.UIExceptionFactory;
import com.system.you.review.web.beans.response.RequestContext;
import com.system.you.review.web.controller.helper.FeedbackHelper;

@Controller
@RequestMapping(value = "/feedback")
public class FeedbackController extends ControllerSupport {

	@RequestMapping(value = "/form/")
	public ModelAndView form(Model model) {
		return new ModelAndView("feedback", model.asMap());
	}

	@RequestMapping(value = "/form/submit/")
	@ResponseStatus(value = HttpStatus.OK)
	public ModelAndView form(FeedbackFormBean formBean, Model model)
			throws UIException {
		RequestContext<FeedbackFormBean, FeedbackViewBean> requestContext = feedbackHelper
				.add(formBean);
		requestContext.setErrorView("error");
		requestContext.setSuccessView("feedbackSuccess");
		requestContext.setModel(model);
		return handleResponse(requestContext, new UIExceptionFactory() {
			@Override
			public UIException create(Model model, String viewName) {
				return new UIException(model, viewName);
			}
		});
	}

	@RequestMapping(value = "/all/")
	@ResponseStatus(value = HttpStatus.OK)
	@Secured({"ADMIN_USER"})
	public ModelAndView all(Model model) throws UIException {
		RequestContext<String, List<FeedbackViewBean>> requestContext = feedbackHelper
				.all();
		requestContext.setErrorView("error");
		requestContext.setSuccessView("feedbackAll");
		requestContext.setModel(model);
		return handleResponse(requestContext, new UIExceptionFactory() {
			@Override
			public UIException create(Model model, String viewName) {
				return new UIException(model, viewName);
			}
		});
	}

	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = { UIException.class })
	public ModelAndView handleException(UIException uiException) {
		return new ModelAndView(uiException.getViewName(), uiException
				.getModel().asMap());
	}

	@Autowired
	private FeedbackHelper feedbackHelper;
}
