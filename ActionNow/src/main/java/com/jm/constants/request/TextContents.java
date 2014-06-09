/**
 * 
 */
package com.jm.constants.request;

/**
 * @author LuZheqi
 * 
 */
public enum TextContents {
	DEVELOPING("开发中，敬请期待！"), SUBMIT_BASIC("请提交基本信息"), MENU_ROOT(
			"请选择以下请求：\n 1 开始事件\n 2 结束事件\n 3 查询积分\n 4 兑换讲评\n"), EVENT_END("事件结束");
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
