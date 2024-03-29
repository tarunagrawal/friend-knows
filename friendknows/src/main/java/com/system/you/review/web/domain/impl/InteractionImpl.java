package com.system.you.review.web.domain.impl;

import java.util.HashMap;
import java.util.Map;

import com.system.you.review.core.service.ServiceLocator;
import com.system.you.review.web.domain.Interaction;
import com.system.you.review.web.domain.Requestor;

public class InteractionImpl implements Interaction {

	
	public InteractionImpl(Requestor requestor, ServiceLocator serviceLocator){
		this.requestor = requestor ;
		this.serviceLocator = serviceLocator;
	}
	
	@Override
	public Requestor getRequestor() {
		return this.requestor;
	}

	public void setRequestor(Requestor requestor) {
		this.requestor = requestor;
	}

	@Override
	public Object getAttribute(String key) {
		return attributes.get(key);
	}
	
	@Override
	public void putAttribute(String key, Object value) {
		attributes.put(key,value);
	}
	
	@Override
	public ServiceLocator getServiceLocator() {
		return serviceLocator;
	}

	private Requestor requestor ;
	private Map<String, Object> attributes = new HashMap<String, Object>();
	private ServiceLocator serviceLocator;
}
