package com.system.you.review.web.review;

import org.springframework.ui.Model;

import com.system.you.review.core.exception.UIException;

public class ReviewCreateException extends UIException {

	public ReviewCreateException(Model model, String viewName) {
		super(model, viewName);
	}

	public ReviewCreateException(Model model, String viewName, String message,
			Throwable ex) {
		super(model, viewName, message, ex);
	}

	private static final long serialVersionUID = 6928297813762432218L;

}
