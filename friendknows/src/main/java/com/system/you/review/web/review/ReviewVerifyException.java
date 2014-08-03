package com.system.you.review.web.review;

import org.springframework.ui.Model;

import com.system.you.review.core.exception.UIException;

public class ReviewVerifyException extends UIException {

	public ReviewVerifyException(Model model, String viewName) {
		super(model, viewName);
	}

	public ReviewVerifyException(Model model, String viewName, String message,
			Throwable ex) {
		super(model, viewName, message, ex);
	}

	private static final long serialVersionUID = 3943485145250780856L;

}
