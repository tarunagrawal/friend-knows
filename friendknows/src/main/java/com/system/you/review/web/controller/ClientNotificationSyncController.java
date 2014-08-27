package com.system.you.review.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.system.you.review.core.exception.UIException;
import com.system.you.review.web.UIExceptionFactory;
import com.system.you.review.web.beans.response.RequestContext;
import com.system.you.review.web.beans.view.NotificationViewBean;
import com.system.you.review.web.controller.helper.NotificationHelper;

@Controller
@RequestMapping(value = "/Notifications")
public class ClientNotificationSyncController extends ControllerSupport {

	@RequestMapping(value = "/")
	@ResponseStatus(value = HttpStatus.OK)
	public ModelAndView notifications(Model model) throws UIException {
		RequestContext<String, NotificationViewBean> requestContext = notificationHelper
				.getDelta();
		requestContext.setModel(model);
		requestContext.setSuccessView("notifications");
		requestContext.setErrorView("error");
		return handleResponse(requestContext, new UIExceptionFactory() {
			@Override
			public UIException create(Model model, String viewName) {
				return new UIException(model, viewName);
			}
		});
	}

	@ExceptionHandler(value = { UIException.class })
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ModelAndView handleExceptions(UIException uiException) {
		return new ModelAndView(uiException.getViewName(), uiException
				.getModel().asMap());

	}

	@Autowired
	private NotificationHelper notificationHelper;
}
