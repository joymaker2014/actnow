/**
 * 
 */
package com.jm.constants.request;

/**
 * @author LuZheqi
 * 
 */
public enum MenuItems {
	ROOT_START_EVENT("1"), ROOT_END_EVENT("2"), ROOT_QUERY_CREDIT("3"), ROOT_EXCHANGE(
			"4");
	private final String _content;

	MenuItems(String content) {
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
