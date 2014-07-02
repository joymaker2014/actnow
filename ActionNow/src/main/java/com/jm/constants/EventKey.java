/**
 * 
 */
package com.jm.constants;

/**
 * @author LuZheqi
 * 
 */
public enum EventKey {
	ROOT_START_EVENT("11"), ROOT_END_EVENT("12"), ROOT_QUERY_EVENT("13"),
	CREDITS_INFO("21"), AWARD_INFO("22"), EXCHANGE("23"), EXCHANGE_QUERY("24"),
	CREDITS_RANKING_LIST("25"), ADD_CREDITS("31"), ADD_AWARDS("32");
	
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
