package com.system.you.review.web.domain;

import com.system.you.review.core.service.ServiceLocator;

public interface Interaction {

	public Requestor getRequestor();
	
	public Object getAttribute(String key);

	public void putAttribute(String key, Object value);

	public ServiceLocator getServiceLocator();

	public static final String REQUEST = "Request";
}
