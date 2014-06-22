/**
 * 
 */
package com.jm.constants;

/**
 * @author LuZheqi
 * 
 */
public enum EventKey {
	ROOT_START_EVENT("11"), ROOT_END_EVENT("12"), ROOT_QUERY_EVENT("13");
	private final String _content;

	EventKey(String content) {
		this._content = content;
	}

	/**
	 * @return the _content
	 */
	@Override
	public String toString() {
		return _content;
	}
}
