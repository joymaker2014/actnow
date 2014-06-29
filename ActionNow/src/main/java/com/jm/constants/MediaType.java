/**
 * 
 */
package com.jm.constants;

/**
 * @author LuZheqi
 * 
 */
public enum MediaType {
	VOICE("voice"), IMAGE("image");
	private final String _mediaType;

	MediaType(String mediaType) {
		this._mediaType = mediaType;
	}

	/**
	 * @return the _eventType
	 */
	@Override
	public String toString() {
		return _mediaType;
	}

}
