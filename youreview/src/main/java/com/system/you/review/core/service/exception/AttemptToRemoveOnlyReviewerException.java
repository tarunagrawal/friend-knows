package com.system.you.review.core.service.exception;


public class AttemptToRemoveOnlyReviewerException extends ServiceException {

	public AttemptToRemoveOnlyReviewerException() {
		super();
	}

	public AttemptToRemoveOnlyReviewerException(String message, Throwable ex) {
		super(message, ex);
	}

	private static final long serialVersionUID = 1L;

}
