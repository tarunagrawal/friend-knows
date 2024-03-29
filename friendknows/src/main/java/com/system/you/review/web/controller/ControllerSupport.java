package com.system.you.review.web.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.owasp.esapi.ESAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.system.you.review.core.constraint.Constraint;
import com.system.you.review.core.constraint.impl.ConstraintFactory;
import com.system.you.review.core.exception.UIException;
import com.system.you.review.user.bean.ReviewUser;
import com.system.you.review.web.UIExceptionFactory;
import com.system.you.review.web.beans.response.RequestContext;
import com.system.you.review.web.domain.Requestor;
import com.system.you.review.web.domain.impl.SessionUtils;

public class ControllerSupport {

	protected Map<String, String> errorMap(Collection<Constraint> constraints) {
		Map<String, String> errorMap = new HashMap<String, String>();
		for (Constraint constraint : constraints) {
			errorMap.put(constraint.getPath(), constraint.getMessage());
		}
		return errorMap;
	}

	protected ReviewUser getLoggedInUser() {
		return getRequestor().getUser();
	}

	protected Requestor getRequestor() {
		return SessionUtils.getRequestor();
	}

	protected <T> Collection<Constraint> validate(T formBean) {
		Set<ConstraintViolation<T>> errors = validator.validate(formBean);
		Collection<Constraint> constraintViolations = new HashSet<Constraint>();
		if (errors != null && !errors.isEmpty()) {
			for (ConstraintViolation<T> constraintViolation : errors) {
				ConstraintFactory constraintCreater = ConstraintFactory.getInstance();
				String path = constraintViolation.getPropertyPath().toString();
				String message = constraintViolation.getMessage();
				Constraint constraint = constraintCreater.getConstraint(path, message);
				constraintViolations.add(constraint);
			}
		}
		return constraintViolations;
	}

	protected String applyXXFilter(String value) {
		return ESAPI.encoder().encodeForHTML(value);
	}

	protected ModelAndView handleResponse(final RequestContext<?, ?> requestContext,
			final UIExceptionFactory exceptionFactory) throws UIException {
		Model model = requestContext.getModel();
		model.addAttribute(FORM_BEAN, requestContext.getFormBean());
		model.addAttribute(VIEW_BEAN, requestContext.getViewBean());
		if (requestContext.containsMessage()) {
			model.addAttribute(MESSAGES, requestContext.getMessages());
			throw exceptionFactory.create(model, requestContext.getErrorView());
		}
		return new ModelAndView(requestContext.getSuccessView(), model.asMap());
	}

	
	@Autowired
	private Validator validator;

	protected static final String JSON = "json";
	protected static final String MESSAGES = "messages";
	protected static final String FORM_BEAN = "form";
	protected static final String VIEW_BEAN = "view";
}
