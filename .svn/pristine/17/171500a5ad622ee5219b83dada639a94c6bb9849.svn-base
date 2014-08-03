package com.system.you.review.core.exception;

import org.springframework.ui.Model;

public class UIException extends ApplicationException {

	public UIException(Model model, String viewName) {
		super();
		this.model = model;
		this.viewName = viewName;
	}

	public UIException(Model model, String viewName, String message,
			Throwable ex) {
		super(message, ex);
		this.model = model;
		this.viewName = viewName;
	}

	public Model getModel() {
		return model;
	}

	public String getViewName() {
		return viewName;
	}

	private Model model;
	private String viewName;

	private static final long serialVersionUID = 751199757724576722L;

}
