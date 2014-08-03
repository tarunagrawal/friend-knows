package com.system.you.review.core.lock;

public interface LockManagerFactory {

	public LockManager getLockManager(Resource resource);
}
