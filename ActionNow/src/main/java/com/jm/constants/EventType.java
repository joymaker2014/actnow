/**
 * 
 */
package com.jm.constants;

/**
 * @author LuZheqi
 * 
 */
public enum EventType {
	SUBSCRIBE("subscribe"), UNSUBSCRIBE("unsubscribe"), SCAN("SCAN"), LOCATION(
			"LOCATION"), CLICK("CLICK"), VIEW("VIEW");
	private final String _eventType;

	EventType(String messagType) {
		this._eventType = messagType;
	}

	/**
	 * @return the _eventType
	 */
	@Override
	public String toString() {
		return _eventType;
	}

}
