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
			"尚有事件未结束，请先结束事件"), PLEASE_START_EVENT("目前尚无事件正在提交中，请首先开始事件"), GET_CREDITS_INFO(
			"您的可用积分为： "), Change_CREDITS_INFO("您添加的可用积分为："), INTRODUCE_AWARD_INFO(
			"目前可兑换的奖品有：\n\r"), SHOPPING_CARD("超市购物卡"), TRANSPORT_CARD("公交一卡通"), TELEPHONE_CARD(
			"手机充值卡"), SHOPPING_CARD_MINI("购物卡"), TRANSPORT_CARD_MINI("一卡通"), TELEPHONE_CARD_MINI(
			"充值卡"), EXCHAGE_INFO(
			"请输入您想兑换的奖品信息，输入格式为：兑换[卡金额]元[奖品类型][兑换数量]张，例如\"兑换50元超市购物卡2张\""), EXCHANGE_VALUE_WRONG(
			"兑换金额不合法，只有20、50、100元三种面额！"), EXCHANGE_NUMBER_WRONG("没有足够数量的卡！"), EXCHANGE_OK(
			"恭喜您，兑换成功！您兑换的结果为"), EXCHANGE_QUERY_INFO("您的兑换记录如下所示：\n\r"), CREDITS_RANKING_INFO("积分排行榜为(名次、用户id、积分)：\n\r");
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
