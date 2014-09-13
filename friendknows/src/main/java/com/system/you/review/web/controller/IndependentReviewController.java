package com.system.you.review.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.system.you.review.category.service.CategoryService;
import com.system.you.review.core.exception.UIException;
import com.system.you.review.web.UIExceptionFactory;
import com.system.you.review.web.beans.form.ReviewFormBean;
import com.system.you.review.web.beans.response.RequestContext;
import com.system.you.review.web.beans.view.ReviewViewBean;
import com.system.you.review.web.controller.helper.AddReviewHelper;
import com.system.you.review.web.review.ReviewCreateException;

@Controller
@RequestMapping(value = "/Review/Independent")
public class IndependentReviewController extends ControllerSupport {

	@RequestMapping(value = "/New")
	@ResponseStatus(value = HttpStatus.OK)
	public String form(Model model) {
		model.addAttribute("categories", categoryService.all());
		return "independentReview";
	}

	@RequestMapping(value = "/New/Submit")
	@ResponseStatus(value = HttpStatus.OK)
	public ModelAndView addSubmit(ReviewFormBean formBean, String category, String item,  Model model)
			throws UIException {
		RequestContext<ReviewFormBean, ReviewViewBean> requestContext = addReviewHelper
				.submitIndependentReview(category,item,formBean);
		requestContext.setSuccessView("json");
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

	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private AddReviewHelper addReviewHelper;

}
