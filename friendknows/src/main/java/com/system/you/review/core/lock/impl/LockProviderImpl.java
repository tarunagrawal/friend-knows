package com.system.you.review.core.lock.impl;

import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.ILock;
import com.system.you.review.core.lock.LockProvider;

public class LockProviderImpl implements LockProvider {

	public LockProviderImpl(HazelcastInstance hzInstance) {
		this.instance = hzInstance;
		this.lockMap = hzInstance.getMap("lockMap_" + this.hashCode());
	}

	@Override
	public boolean lock(String key) {
		boolean acquired = false;
		Lock lock = null;
		try {
			// if map already has this entry this means somebody has acquired
			// lock
			if (lockMap.containsKey(key)) {
				return false;
			}
			// map does not have entry, try to get the lock and acquire
			lock = getLock(key);
			acquired = ((ILock) lock).tryLock(LOCK_ATTEMPT_TIME_OUT, TimeUnit.MILLISECONDS);

			if (acquired) {
				// lock acquired, insert an entry to map so that all node see
				// it.
				lockMap.put(key, "acquired");
			}
			if (logger.isDebugEnabled()) {
				logger.debug("lock aquired on " + key);
			}
			return acquired;
		} catch (Exception ex) {
			logger.error("error while acquiring the lock ", ex);
		} finally {
			// release the lock, if it was acquired
			if (acquired && lock != null) {
				lock.unlock();
			}
		}
		return acquired;
	}

	@Override
	public boolean unlock(String key) {
		boolean acquired = false;
		Lock lock = null;
		try {
			if (!lockMap.containsKey(key)) {
				// Map does not have entry means already released.
				logger.error("lock was already released for " + key);
				return true;
			}
			// map has the entry, try to get the lock and acquire
			lock = getLock(key);
			acquired = ((ILock) lock).tryLock(LOCK_ATTEMPT_TIME_OUT, TimeUnit.MILLISECONDS);
			if (acquired) {
				lockMap.remove(key);
			}
			if (logger.isDebugEnabled()) {
				logger.debug("lock released on " + key);
			}
			return true;
		} catch (Exception ex) {
			logger.error("error while acquiring the lock ", ex);
		} finally {
			// release the lock, if it was acquired
			if (acquired && lock != null) {
				lock.unlock();
			}
		}
		return false;
	}

	private Lock getLock(String key) {
		return instance.getLock(key);
	}

	private Map<String, String> lockMap;

	private HazelcastInstance instance;

	private static int LOCK_ATTEMPT_TIME_OUT = 200;
	private Logger logger = LoggerFactory.getLogger(LockProviderImpl.class);

}
