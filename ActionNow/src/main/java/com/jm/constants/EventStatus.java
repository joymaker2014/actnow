/**
 * 
 */
package com.jm.constants;

/**
 * @author LuZheqi
 * 
 */
public enum EventStatus {

	GPSOK("gpsok"), STARTTING("starting"), BASICOK("basicok");

	private final String _content;

	EventStatus(String content) {
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
