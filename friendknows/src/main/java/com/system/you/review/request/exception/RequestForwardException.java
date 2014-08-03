package com.system.you.review.request.exception;

import org.springframework.ui.Model;

import com.system.you.review.core.exception.UIException;

public class RequestForwardException extends UIException {

	public RequestForwardException(Model model, String viewName) {
		super(model, viewName);
	}

	public RequestForwardException(Model model, String viewName,
			String message, Throwable throwable) {
		super(model, viewName, message, throwable);
	}

	private static final long serialVersionUID = 8849539517775897913L;
}
