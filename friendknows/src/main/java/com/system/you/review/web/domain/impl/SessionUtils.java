package com.system.you.review.web.domain.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.system.you.review.core.service.ServiceLocator;
import com.system.you.review.web.domain.Interaction;
import com.system.you.review.web.domain.Requestor;

public class SessionUtils {

	public static void setCurrent(Interaction value,
			HttpServletRequest httpRequest, HttpServletResponse httpRespone) {
		interaction.set(value);
		currentRequest.set(httpRequest);
		currentResponse.set(httpRespone);
		httpRequest.setAttribute("user", value.getRequestor().getUser());
	}

	public static void clearCurrent() {
		interaction.set(null);
		currentRequest.set(null);
		currentResponse.set(null);
	}

	public static HttpServletRequest getCurrentRequest() {
		return currentRequest.get();
	}

	public static HttpServletResponse getCurrentResponse() {
		return currentResponse.get();
	}

	public static Requestor getRequestor() {
		if (interaction.get() != null) {
			return interaction.get().getRequestor();
		}
		return null;
	}

	public static ServiceLocator getServiceLocator() {
		return interaction.get().getServiceLocator();
	}

	private static ThreadLocal<Interaction> interaction = new ThreadLocal<Interaction>();
	private static ThreadLocal<HttpServletRequest> currentRequest = new ThreadLocal<HttpServletRequest>();
	private static ThreadLocal<HttpServletResponse> currentResponse = new ThreadLocal<HttpServletResponse>();
}
