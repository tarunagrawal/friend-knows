package com.system.you.review.core.lock.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.you.review.core.cluster.Cluster;
import com.system.you.review.core.lock.LockManager;
import com.system.you.review.core.lock.LockManagerFactory;
import com.system.you.review.core.lock.Resource;

@Service
public class LockManagerFactoryImpl implements LockManagerFactory {

	private LockManagerFactoryImpl() {
		lockManagers = new ConcurrentHashMap<String, LockManager>();
	}

	@Override
	public synchronized LockManager getLockManager(Resource resource) {
		if (!lockManagers.containsKey(resource.getIdentity())) {
			LockManager lockManager = new LockManagerImpl(resource,cluster.getLockProvider());
			lockManagers.put(resource.getIdentity(), lockManager);
			return lockManager;
		} else {
			return lockManagers.get(resource.getIdentity());
		}
	}


	@Autowired
	private Cluster cluster ;

	private Map<String, LockManager> lockManagers;
}
