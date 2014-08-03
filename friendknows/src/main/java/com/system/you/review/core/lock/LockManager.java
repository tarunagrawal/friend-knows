package com.system.you.review.core.lock;

public interface LockManager {

	public boolean tryLock(String key);
	
	public boolean unlock(String key);
}
