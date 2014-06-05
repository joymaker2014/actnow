/**
 * 
 */
package com.jm.constants.request;

/**
 * @author LuZheqi
 *
 */
public enum TextContents {
	DEVELOPING("开发中，敬请期待！");
	private final String _content;

	TextContents(String content) {
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
