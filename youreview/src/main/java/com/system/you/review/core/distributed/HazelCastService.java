package com.system.you.review.core.distributed;

import com.hazelcast.core.HazelcastInstance;

public interface HazelCastService {

	public HazelcastInstance getHazelInstance();
}
