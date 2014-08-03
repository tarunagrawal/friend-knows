package com.system.you.review.request.exception;

import org.springframework.ui.Model;

import com.system.you.review.core.exception.UIException;

public class CreateRequestException extends UIException {
	
	public CreateRequestException(Model model, String viewName){
		super(model,viewName);
	}
	
	private static final long serialVersionUID = 8849539517775897913L;
}
