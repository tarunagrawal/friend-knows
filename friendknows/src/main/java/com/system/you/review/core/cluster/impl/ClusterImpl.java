package com.system.you.review.core.cluster.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hazelcast.core.HazelcastInstance;
import com.system.you.review.core.cluster.Cluster;
import com.system.you.review.core.lock.LockProvider;
import com.system.you.review.core.lock.impl.LockProviderImpl;

@Service
public class ClusterImpl implements Cluster {

	@Override
	public LockProvider getLockProvider() {
		return new LockProviderImpl(instance);
	}

	@Autowired
	private HazelcastInstance instance;
}
