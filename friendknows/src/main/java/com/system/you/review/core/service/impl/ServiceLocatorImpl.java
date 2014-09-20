package com.system.you.review.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.you.review.core.service.ServiceLocator;
import com.system.you.review.item.bean.helper.impl.UserBeanHelper;
import com.system.you.review.user.service.ReviewUserService;

@Service
public class ServiceLocatorImpl implements ServiceLocator {

	@Override
	public Object getService(ServiceName serviceName) {
		Object service = null;
		switch (serviceName) {
		case USER_SERVICE:
			service = reviewUserService;
			break;

		default:
			break;
		}
		return service;
	}
	
	@Override
	public ReviewUserService getUserService() {
		return reviewUserService;
	}
	
	@Override
	public UserBeanHelper getUserBeanHelper() {
		return userBeanHelper;
	}
	
	@Autowired
	private ReviewUserService reviewUserService;
	
	@Autowired
	private UserBeanHelper userBeanHelper;
	
}
