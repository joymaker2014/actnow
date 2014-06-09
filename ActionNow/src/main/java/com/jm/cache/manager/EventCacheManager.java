/**
 * 
 */
package com.jm.cache.manager;

import com.jm.cache.CacheImpl;
import com.jm.cache.spi.Cache;
import com.jm.model.OriginalEvent;

/**
 * @author LuZheqi
 * 
 */
public class EventCacheManager {
	private static volatile EventCacheManager instance = null;
	private final Cache<String, OriginalEvent> cache = new CacheImpl<String, OriginalEvent>(
			"eventcache");

	private EventCacheManager() {

	}

	public static EventCacheManager getInstance() {
		if (null == instance) {
			synchronized (EventCacheManager.class) {
				if (null == instance) {
					instance = new EventCacheManager();
				}
			}
		}
		return instance;
	}

	/**
	 * @return the cache
	 */
	public Cache<String, OriginalEvent> getCache() {
		return cache;
	}

}
