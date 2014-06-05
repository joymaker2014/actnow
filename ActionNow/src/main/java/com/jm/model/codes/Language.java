/**
 * 
 */
package com.jm.model.codes;

/**
 * @author LuZheqi
 * 
 */
public enum Language {
	ZH_CN("zh_CN");

	private final String _desc;

	Language(String desc) {
		_desc = desc;
	}

	public String getDesc() {
		return _desc;
	}
}
