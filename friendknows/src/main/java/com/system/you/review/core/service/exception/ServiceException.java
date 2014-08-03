package com.system.you.review.core.service.exception;

import com.system.you.review.core.exception.ApplicationException;

public class ServiceException extends ApplicationException {

	public ServiceException() {
		super();
	}

	public ServiceException(String message) {
		super(message);
	}

	
	public ServiceException(String message, Throwable ex) {
		super(message, ex);
	}

	private static final long serialVersionUID = 1L;

}
