package com.system.you.review.core.service;

import com.system.you.review.item.bean.helper.impl.UserBeanHelper;
import com.system.you.review.user.service.ReviewUserService;

public interface ServiceLocator {
	
	public Object getService(ServiceName serviceName);

	public ReviewUserService getUserService();
	
	public UserBeanHelper getUserBeanHelper();
	
	public enum ServiceName{
			USER_SERVICE, 
	} 
}
