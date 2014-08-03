package com.system.you.review.web.controller.helper;

import java.util.Locale;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import com.system.you.review.web.beans.response.RequestContext;
import com.system.you.review.web.domain.impl.SessionUtils;

public class ControllerHelper {

	protected <T, V>  void validate(T formBean, RequestContext<T, V> responseBean) {
		Set<ConstraintViolation<T>> errors = validator.validate(formBean);
		if (errors != null && !errors.isEmpty()) {
			for (ConstraintViolation<T> violation : errors) {
				String path = violation.getPropertyPath().toString();
				String message = violation.getMessage();
				responseBean.addMessage(path, message);
			}
		}
	}

	protected Locale getLocale(){
		return SessionUtils.getRequestor().getLocale();
	}
	
	protected String getMessage(String code, Object[] args) {
		return messageSource.getMessage(code, args, getLocale());
	}

	protected <T, V> void addSystemErrorMessage(
			RequestContext<T, V> respBean) {
		respBean.addMessage(SYSTEM_ERROR_CODE,
				getMessage(SYSTEM_ERROR_CODE, null));
	}
	
	@Autowired
	private Validator validator;
	
	@Autowired
	private MessageSource messageSource;
	
	protected static final String SYSTEM_ERROR_CODE = "system.error";
}
