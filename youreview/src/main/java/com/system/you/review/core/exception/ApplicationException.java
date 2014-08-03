package com.system.you.review.core.exception;

public class ApplicationException extends Exception {

	public ApplicationException() {
		super();
	}
	
	public ApplicationException(String message) {
		super(message);
	}

	public ApplicationException(String message, Throwable throwable) {
		super(message, throwable);
	}

	private static final long serialVersionUID = 3544966217157207824L;

}
