/**
 * 
 */
package com.jm.cache.exception;

import com.jm.cache.spi.Cache;

/**
 * @author LuZheqi
 * 
 */
public class CacheEntryNotExistsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public <K, V> CacheEntryNotExistsException(Cache<K, V> cache, K keys) {
		super();
	}

}
