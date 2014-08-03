package com.system.you.review.web;

import org.springframework.ui.Model;

import com.system.you.review.core.exception.UIException;

public interface UIExceptionFactory {

	public UIException create(Model model, String viewName);
}
