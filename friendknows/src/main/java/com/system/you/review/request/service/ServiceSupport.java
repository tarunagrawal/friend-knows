package com.system.you.review.request.service;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.system.you.review.ApplicationEntity;
import com.system.you.review.core.cache.CacheRegistry;
import com.system.you.review.user.bean.ReviewUser;
import com.system.you.review.web.domain.impl.SessionUtils;

public class ServiceSupport {

	protected ReviewUser getCurrentUser() {
		return SessionUtils.getRequestor().getUser();
	}

	public boolean alreadyExist(ApplicationEntity entity) {
		return !StringUtils.isBlank(entity.getId());
	}

	protected boolean hasRight(ApplicationEntity instance) throws SecurityException {
		if (!alreadyExist(instance)) {
			return true;
		}

		String onwer = instance.getOwnerId();
		// check if system is owner of the entity - all users are allowed
		if (onwer.equalsIgnoreCase(ApplicationEntity.OWNER.SYSTEM.getValue())) {
			return true;
		}

		ReviewUser currentUser = getCurrentUser();
		if (StringUtils.isNotBlank(onwer)) {
			if (currentUser.getProviderUserId().equalsIgnoreCase(onwer)) {
				return true;
			}
		}

		String message = currentUser.getName() + " does not have access to entity {"
				+ instance.getName() + "}";
		log.error(message);
		throw new SecurityException(message);
	}

	protected void triggerAddChange(Object bean) {
		cacheRegistry.triggerAddChangeForCaches(bean);
	}

	protected void triggerDeleteChange(Object bean) {
		cacheRegistry.triggerDeleteChangeForCaches(bean);
	}

	protected void triggerModifyChange(Object bean) {
		cacheRegistry.triggerModifyChangeForCaches(bean);
	}

	@Autowired
	private CacheRegistry cacheRegistry;

	private static Logger log = LoggerFactory.getLogger(ServiceSupport.class);
}
