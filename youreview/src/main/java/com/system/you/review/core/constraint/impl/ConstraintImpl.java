package com.system.you.review.core.constraint.impl;

import com.system.you.review.core.constraint.Constraint;

public class ConstraintImpl implements Constraint{

	ConstraintImpl(String path, String message) {
		this.path = path;
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	private String path;
	private String message;
}
