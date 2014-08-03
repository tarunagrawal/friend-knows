package com.system.you.review.core.distributed.impl;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.system.you.review.core.distributed.HazelCastService;

public class HazelCastServiceImpl implements HazelCastService {

	@Override
	public HazelcastInstance getHazelInstance() {
		
		for (HazelcastInstance instance : Hazelcast.getAllHazelcastInstances()) {
			System.out.println(instance.getName());
		} 
		
		return Hazelcast.getHazelcastInstanceByName("review-hazelcast-instance");
	}

	public static HazelCastService getInstance() {
		return hazelCastService;
	}

	private static HazelCastService hazelCastService = new HazelCastServiceImpl();
}
