/**
 * 
 */
package com.jm.constants.request;

/**
 * @author LuZheqi
 * 
 */
public enum RequestKeys {
	TOUSERNAME("ToUserName"), FROMUSERNAME("FromUserName"), CREATETIME(
			"CreateTime"), MSGTYPE("MsgType"), CONTENT("Content"), MSGID(
			"MsgId"), PICURL("PicUrl"), MEDIAID("MediaId"), FORMAT("Format"), THUMBMEDIAID(
			"ThumbMediaId"), LATITUDE("Latitude"), LONGITUDE("Longitude"), PRECISION(
			"Precision"), Scale("Scale"), LABEL("Label"), TITLE("Title"), DESCRIPTION(
			"Description"), URL("Url"), EVENT("Event"), EVENTKEY("EventKey"), LOCATION_X(
			"Location_X"), LOCATION_Y("Location_Y");
	private final String _key;

	RequestKeys(String key) {
		this._key = key;
	}

	/**
	 * @return the _key
	 */
	@Override
	public String toString() {
		return _key;
	}
}
