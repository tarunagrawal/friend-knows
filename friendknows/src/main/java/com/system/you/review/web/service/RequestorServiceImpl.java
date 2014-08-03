package com.system.you.review.web.service;

import java.security.SecureRandom;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.system.you.review.web.domain.Requestor;
import com.system.you.review.web.domain.impl.WebRequestorImpl;

@Service
public class RequestorServiceImpl implements RequestorService {

	public RequestorServiceImpl() {
		secureRandom = new SecureRandom();
		requestors = new ConcurrentHashMap<String, Requestor>();
	}

	@Override
	public synchronized Requestor getRequestor(String id) {
		return requestors.get(id);
	}

	@Override
	public synchronized boolean addRequestor(Requestor requestor) {
		requestors.put(requestor.getId(), requestor);
		return true;
	}

	@Override
	public synchronized boolean removeRequestor(String id) {
		return (requestors.remove(id) == null) ? false : true;
	}

	@Override
	public synchronized Requestor create(String suggestedName) {
		Requestor requestor = null;
		String name = "";

		if (StringUtils.isBlank(suggestedName)) {
			name = String.valueOf(secureRandom.nextInt());
		} else if (exist(suggestedName)) {
			logger.error("requestor with id " + suggestedName
					+ " already exist, a new requestor will be created");
			name = String.valueOf(secureRandom.nextInt());
		} else {
			name = suggestedName;
		}

		requestor = WebRequestorImpl.createRequestor(name);
		addRequestor(requestor);

		return requestor;
	}

	@Override
	public synchronized boolean exist(String id) {
		return requestors.containsKey(id);
	}

	private Map<String, Requestor> requestors;

	private SecureRandom secureRandom;

	private Logger logger = LoggerFactory.getLogger(RequestorServiceImpl.class);

}
