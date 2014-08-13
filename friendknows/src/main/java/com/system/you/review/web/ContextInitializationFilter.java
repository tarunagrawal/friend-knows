package com.system.you.review.web;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.GenericFilterBean;

import com.system.you.review.core.service.ServiceLocator;
import com.system.you.review.web.domain.Interaction;
import com.system.you.review.web.domain.Requestor;
import com.system.you.review.web.domain.impl.InteractionImpl;
import com.system.you.review.web.domain.impl.SessionUtils;
import com.system.you.review.web.service.RequestorService;
import com.system.you.review.web.utils.HttpHelper;

public class ContextInitializationFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		try {
			establishContext(httpRequest, httpResponse);
			chain.doFilter(request, response);
		} finally {
			resetContext(httpRequest);
		}
	}

	private void resetContext(HttpServletRequest servletRequest) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("reseting context");
		}

		Requestor requestor = SessionUtils.getRequestor();
		if (requestor != null && requestor.isDestroyed()) {
			requestorService.removeRequestor(requestor.getId());
			clearSession(servletRequest);
		}
		SessionUtils.clearCurrent();
	}

	private void establishContext(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("establishing context");
		}

		Requestor requestor = null;
		try {
			requestor = findRequestor(request);
			Interaction interaction = getInteraction(requestor);
			SessionUtils.setCurrent(interaction, request, response);
		} catch (Exception ex) {
			response.sendRedirect(HttpHelper.getHomeURL(request));
		}
	}

	private InteractionImpl getInteraction(Requestor requestor) {
		return new InteractionImpl(requestor, serviceLocator);
	}

	private Requestor findRequestor(HttpServletRequest request)
			throws RequestorNoFoundException {
		Requestor requestor = null;
		// this synchronization make sure that two parallel request in same
		// session should not create two Requestor
		synchronized (request.getSession()) {
			String requestorId = getRequestorId(request);
			if (Requestor.NEW_REQUESTOR.equalsIgnoreCase(requestorId)
					|| StringUtils.isBlank(requestorId)) {
				requestor = createRequestor(request, requestorId);
				return requestor;
			}
			requestor = getExistingRequestor(requestorId);
			if (requestor == null) {
				// in any case existing requestor in null
				requestor = createRequestor(request, requestorId);
			}
		}
		return requestor;
	}

	private Requestor createRequestor(HttpServletRequest request,
			String requestorId) {
		Requestor requestor;
		requestor = requestorService.create(requestorId);
		setSession(request, requestor);
		return requestor;
	}

	private void setSession(HttpServletRequest request, Requestor requestor) {
		request.getSession().setAttribute(Requestor.REQUESTOR_ID,
				requestor.getId());
	}

	private Requestor getExistingRequestor(String requestorId) {
		Requestor requestor = requestorService.getRequestor(requestorId);
		return requestor;
	}

	private String getRequestorId(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.isNew()) {
			return Requestor.NEW_REQUESTOR;
		}
		String requestorId = (String) session
				.getAttribute(Requestor.REQUESTOR_ID);
		return requestorId;
	}

	private void clearSession(HttpServletRequest servletRequest) {
		HttpSession session = servletRequest.getSession(false);
		if (session != null) {
			session.removeAttribute(Requestor.REQUESTOR_ID);
			session.invalidate();
		}
	}

	@Autowired
	private ServiceLocator serviceLocator;

	@Autowired
	private RequestorService requestorService;

	private static Logger LOGGER = LoggerFactory
			.getLogger(ContextInitializationFilter.class);
}
