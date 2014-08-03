package com.system.you.review.core.lock.impl;

import com.system.you.review.core.lock.LockManager;
import com.system.you.review.core.lock.LockProvider;
import com.system.you.review.core.lock.Resource;

public class LockManagerImpl implements LockManager {

	public LockManagerImpl(Resource resource, LockProvider lockProvider) {
		this.resource = resource;
		this.lockProvider = lockProvider;
	}

	@Override
	public boolean tryLock(String key) {
		return lockProvider.lock(getLockKey(key));
	}

	@Override
	public boolean unlock(String key) {
		return lockProvider.unlock(getLockKey(key));
	}
	

	private String getLockKey(String key) {
		return this.resource.getIdentity() + "." + key;
	}

	private LockProvider lockProvider;

	private Resource resource;
}
