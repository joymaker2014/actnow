/**
 * 
 */
package com.jm.constants.request;

/**
 * @author LuZheqi
 * 
 */
public enum TextContents {
	WELCOME("欢迎来到JoyMaker！！！"), DEVELOPING("开发中，敬请期待！"), SUBMIT_BASIC("请提交基本信息"), MENU_ROOT(
			"请选择以下请求：\n 1 开始事件\n 2 结束事件\n 3 查询事件\n 4 查询积分\n 5 兑换奖品"), EVENT_END(
			"事件提交成功，谢谢！"), RECEIVE_OK("接收成功！"), PLAESE_END_EVENT(
			"尚有事件未结束，请先结束事件"), PLEASE_START_EVENT("目前尚无事件正在提交中，请首先开始事件"), BASICINFO(
			"基本信息"), IMAGE("图片"), VOICE("语音"), LOCATION("位置"), DESCRIPTIONS(
			"描述文字"), PROVIDE_MORE("为了提高事件处理的便利，请提交XXX信息；若不需要提交请再次点击结束事件按钮！"), GET_CREDITS_INFO(
			"您的可用积分为： "), Change_CREDITS_INFO("您添加的可用积分为："), INTRODUCE_AWARD_INFO(
			"目前可兑换的奖品有：\n\r"), SHOPPING_CARD("超市购物卡"), TRANSPORT_CARD("公交一卡通"), TELEPHONE_CARD(
			"手机充值卡"), EXCHAGE_INFO("请输入您想兑换的奖品前面的序号");
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
