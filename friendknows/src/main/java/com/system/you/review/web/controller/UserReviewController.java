package com.system.you.review.web.controller;

import java.util.List;

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
import com.system.you.review.web.beans.view.ReviewViewBean;
import com.system.you.review.web.controller.helper.UserReviewHelper;

@Controller
@RequestMapping(value = "/My/Reviews/")
public class UserReviewController extends ControllerSupport {

	@RequestMapping(value = "")
	@ResponseStatus(value = HttpStatus.OK)
	public ModelAndView reviews(Model model) throws UIException{
		RequestContext<String, List<ReviewViewBean>> requestContext = userReviewHelper
				.currentUserReviews();
		requestContext.setErrorView("error");
		requestContext.setSuccessView("myReviews");
		requestContext.setModel(model);
		return handleResponse(requestContext, new UIExceptionFactory() {
			@Override
			public UIException create(Model model, String viewName) {
				return new UIException(model, viewName);
			}
		});
	}

	@ExceptionHandler
	@ResponseStatus(value=HttpStatus.BAD_REQUEST)
	public ModelAndView handleException(UIException exception){
		return new ModelAndView(exception.getViewName(), exception.getModel().asMap());
	}
	
	@Autowired
	private UserReviewHelper userReviewHelper;

}
