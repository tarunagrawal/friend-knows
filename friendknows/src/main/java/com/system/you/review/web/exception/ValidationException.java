package com.system.you.review.web.exception;

import java.util.Map;

@SuppressWarnings("serial")
public class ValidationException extends Exception {

	public ValidationException(Object formBean) {
		this.formBean = formBean;
		this.validationMessage = null;
	}

	public ValidationException(Map<String, String> validationMessage) {
		this.validationMessage = validationMessage;
	}

	public Map<String, String> getValidationMessage() {
		return validationMessage;
	}

	public void setValidationMessage(Map<String, String> validationMessage) {
		this.validationMessage = validationMessage;
	}

	public Object getFormBean() {
		return formBean;
	}

	public void setFormBean(Object formBean) {
		this.formBean = formBean;
	}

	private Map<String, String> validationMessage;
	private Object formBean;
}
