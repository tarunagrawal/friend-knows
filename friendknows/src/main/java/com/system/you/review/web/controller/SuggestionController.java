package com.system.you.review.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.system.you.review.core.exception.UIException;
import com.system.you.review.web.UIExceptionFactory;
import com.system.you.review.web.beans.response.RequestContext;
import com.system.you.review.web.beans.view.UserViewBean;
import com.system.you.review.web.controller.helper.SuggestionHelper;

@Controller
@RequestMapping(value = "/suggestion")
public class SuggestionController extends ControllerSupport {

	@RequestMapping("/location/friend")
	@ResponseStatus(value = HttpStatus.OK)
	public ModelAndView friends(@RequestParam String location, Model model)
			throws UIException {
		RequestContext<String, List<UserViewBean>> requestContext = suggestionHelper
				.locationSuggestions(location);
		requestContext.setErrorView("error");
		requestContext.setSuccessView("friendsWithLocation");
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
	public ModelAndView exceptionHandler(UIException uiException) {
		return new ModelAndView(uiException.getViewName(), uiException
				.getModel().asMap());
	}

	@Autowired
	private SuggestionHelper suggestionHelper;
}
