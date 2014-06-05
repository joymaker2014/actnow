/**
 * 
 */
package com.jm.constants.response;

/**
 * @author LuZheqi
 * 
 */
public enum ResponseKeys {
	TOUSERNAME("ToUserName"), FROMUSERNAME("FromUserName"), CREATETIME(
			"CreateTime"), MSGTYPE("MsgType"), CONTENT("Content"), MEDIAID(
			"MediaId"), IMAGE("Image"), VOICE("Voice"), VIDEO("Video"), TITLE(
			"Title"), DESCRIPTION("Description"), MUSIC("Music"), MUSICURL(
			"MusicUrl"), HQMUSICURL("HQMusicUrl"), THUMBMEDIAID("ThumbMediaId"), ARTICLECOUNT(
			"ArticleCount"), ARTICLES("Articles"), ITEM("item"), PICURL(
			"PicUrl"), URL("Url");
	
	private final String _key;

	ResponseKeys(String key) {
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
