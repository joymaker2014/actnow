/**
 * 
 */
package com.jm.cache.exception;

import java.util.List;

import com.jm.cache.spi.Cache;

/**
 * @author LuZheqi
 * 
 */
public class CacheEntriesNotExistException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public <K, V> CacheEntriesNotExistException(Cache<K, V> cache, List<K> keys) {
		super();
	}

}
