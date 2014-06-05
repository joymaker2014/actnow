/**
 * 
 */
package com.jm.constants;

/**
 * @author LuZheqi
 * 
 */
public enum MessageType {
	Text("text"), Image("image"), Music("music"), Video("video"), Voice("voice"), Location(
			"location"), Link("link"), Event("event");
	private final String _messageType;

	MessageType(String messagType) {
		this._messageType = messagType;
	}

	/**
	 * @return the _messageType
	 */
	@Override
	public String toString() {
		return _messageType;
	}

}
