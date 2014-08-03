package com.system.you.review.core.cache;

public interface CacheService {

	public void triggerChange(CacheEvent event);
	
	public void registerCacheWithRegistry();
}
