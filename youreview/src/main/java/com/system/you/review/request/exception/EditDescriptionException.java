package com.system.you.review.request.exception;

import org.springframework.ui.Model;

import com.system.you.review.core.exception.UIException;

public class EditDescriptionException extends UIException {

	public EditDescriptionException(Model model, String viewName) {
		super(model, viewName);
	}

	public EditDescriptionException(Model model, String viewName,
			String message, Throwable ex) {
		super(model, viewName, message, ex);
	}

	private static final long serialVersionUID = 484813163550028022L;

}
