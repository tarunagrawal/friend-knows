package com.system.you.review.core.cache.impl;

import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.you.review.core.cache.CacheEvent;
import com.system.you.review.core.cache.CacheRegistry;
import com.system.you.review.core.cache.CacheService;
import com.system.you.review.core.service.TagService;

@Service
public class CacheRegistryImpl implements CacheRegistry {

	@Autowired
	private TagService tagService;

	@Override
	public void triggerChangeForCaches(CacheEvent event) {
		for (Entry<String, CacheService> caheEntry : caches.entrySet()) {
			caheEntry.getValue().triggerChange(event);
		}
	}

	@Override
	public void registerCacheService(CacheService cacheService) {
		String cacheKey = cacheService.getClass().getName();
		if (!caches.containsKey(cacheKey)) {
			caches.put(cacheKey, cacheService);
		} else {
			System.out.println("Already Registerred");
		}
	}

	@Override
	public void triggerAddChangeForCaches(Object bean) {
		triggerChangeForCaches(CacheEvent.addEvent(bean));
	}

	@Override
	public void triggerModifyChangeForCaches(Object bean) {
		triggerChangeForCaches(CacheEvent.modifyEvent(bean));
	}

	@Override
	public void triggerDeleteChangeForCaches(Object bean) {
		triggerChangeForCaches(CacheEvent.deleteEvent(bean));
	}

	private Map<String, CacheService> caches = new ConcurrentHashMap<String, CacheService>();

}
