package com.system.you.review.web.reviewer.exception;

import org.springframework.ui.Model;

import com.system.you.review.core.exception.UIException;

public class AddReviewerException extends UIException {

	public AddReviewerException(Model model, String viewName) {
		super(model, viewName);
	}

	public AddReviewerException(Model model, String viewName,
			String message, Throwable ex) {
		super(model, viewName, message, ex);
	}

	private static final long serialVersionUID = -3830033588917726958L;

}
