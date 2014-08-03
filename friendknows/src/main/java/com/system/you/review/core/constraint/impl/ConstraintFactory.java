package com.system.you.review.core.constraint.impl;

import com.system.you.review.core.constraint.Constraint;

public class ConstraintFactory {

	public static ConstraintFactory getInstance() {
		return instance;
	}

	public Constraint getConstraint(String path, String message) {
		return new ConstraintImpl(path, message);
	}

	private ConstraintFactory() {
	}

	private static ConstraintFactory instance = new ConstraintFactory();
}
