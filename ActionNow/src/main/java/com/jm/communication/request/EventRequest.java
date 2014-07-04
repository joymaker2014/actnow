/**
 * 
 */
package com.jm.communication.request;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.jm.cache.manager.EventCacheManager;
import com.jm.client.WeixinClient;
import com.jm.constants.EventKey;
import com.jm.constants.EventStatus;
import com.jm.constants.EventType;
import com.jm.constants.GoodsType;
import com.jm.constants.request.RequestKeys;
import com.jm.constants.request.TextContents;
import com.jm.model.OriginalEvent;
import com.jm.model.User;
import com.jm.timer.event.EventTimeoutClient;
import com.jm.timer.event.EventTimeoutTask;
import com.jm.util.CommonUtils;
import com.jm.util.ServiceUtils;
//github.com/joymaker2014/actnow.git
import com.jm.model.ExchangeRecord;
import com.jm.model.Goods;
import com.jm.model.UserTrace;

/**
 * @author LuZheqi
 * 
 */
public class EventRequest {

	public String handle(Map<String, String> datas) {
		String eventType = CommonUtils
				.getRequestValue(datas, RequestKeys.EVENT);
		String openid = datas.get(RequestKeys.FROMUSERNAME.toString());
		User existUser = ServiceUtils.getUserService().findUserById(openid);
		if (eventType.equalsIgnoreCase(EventType.SUBSCRIBE.toString())) {
			if (null != existUser) {
				existUser.setSubscribe(true);
				existUser.setSubscribeTime(new Date());
			} else {
				existUser = WeixinClient.getUserInfo(openid);
			}
			ServiceUtils.getUserService().saveUser(existUser);
			return EventType.SUBSCRIBE.toString();
		} else if (eventType.equalsIgnoreCase(EventType.UNSUBSCRIBE.toString())) {
			if (null != existUser) {
				existUser.setSubscribe(false);
				existUser.setSubscribeTime(new Date());
				ServiceUtils.getUserService().saveUser(existUser);
			}
			return EventType.UNSUBSCRIBE.toString();
		} else if (eventType.equalsIgnoreCase(EventType.CLICK.toString())) {
			String eventKey = CommonUtils.getRequestValue(datas,
					RequestKeys.EVENTKEY);
			OriginalEvent event = EventCacheManager.getInstance().getCache()
					.get(openid);
			if (eventKey.equalsIgnoreCase(EventKey.ROOT_START_EVENT.toString())) {
				if (null == event) {
					OriginalEvent oevent = new OriginalEvent();
					oevent.setId(UUID.randomUUID().toString());
					oevent.setStatus(EventStatus.STARTTING);
					oevent.setUser(ServiceUtils.getUserService().findUserById(
							openid));
					oevent.setTime(new Date());
					EventCacheManager.getInstance().getCache()
							.put(openid, oevent);
					new EventTimeoutTask(new EventTimeoutClient(openid,
							oevent.getId()));
					return EventStatus.STARTTING.toString();
				} else {
					return TextContents.PLAESE_END_EVENT.toString();
				}
			} else if (eventKey.equalsIgnoreCase(EventKey.ROOT_END_EVENT
					.toString())) {
				if (null == event) {
					return TextContents.WELCOME.toString();
				} else {
					if (!event.getStatus().equals(EventStatus.ENDDING)) {
						String checkResult = checkEvent(event);
						if (null != checkResult) {
							event.setStatus(EventStatus.ENDDING);
							return TextContents.PROVIDE_MORE.toString()
									.replaceAll("XXX", checkResult);
						}
					}
					ServiceUtils.getOriginalEventService().saveOriginalEvent(
							event);
					EventCacheManager.getInstance().getCache()
							.invalidate(openid);
					return TextContents.EVENT_END.toString();
				}

			} else if (eventKey.equalsIgnoreCase(EventKey.ROOT_QUERY_EVENT
					.toString())) {
				StringBuilder sb = new StringBuilder();
				sb.append("<a href=\"");
				sb.append(CommonUtils.getProperties("url.base",
						"http://127.0.0.1:8080/"));
				sb.append("events.jsp?openid=" + openid + "\">请点击这里查看</a>");
				return sb.toString();
			} else if (eventKey.equalsIgnoreCase(EventKey.CREDITS_INFO
					.toString())) {
				if (null != existUser) {
					return TextContents.GET_CREDITS_INFO.toString()
							+ existUser.getCredit();
				} else {
					return TextContents.GET_CREDITS_INFO.toString() + "-1";
				}
			} else if (eventKey
					.equalsIgnoreCase(EventKey.AWARD_INFO.toString())) {
				StringBuffer sb = new StringBuffer();
				sb.append(TextContents.INTRODUCE_AWARD_INFO);
				sb.append("1. " + TextContents.SHOPPING_CARD + ":\n\r");
				sb.append("\t\t20元\t"
						+ ServiceUtils
								.getGoodsService()
								.countCardNumGroupByTypeAndValue(
										Integer.parseInt(GoodsType.SHOPPING_CARD.toString()),
										20) + " 张\n\r");
				sb.append("\t\t50元\t"
						+ ServiceUtils
								.getGoodsService()
								.countCardNumGroupByTypeAndValue(
										Integer.parseInt(GoodsType.SHOPPING_CARD.toString()),
										50) + " 张\n\r");
				sb.append("\t\t100元\t"
						+ ServiceUtils
								.getGoodsService()
								.countCardNumGroupByTypeAndValue(
										Integer.parseInt(GoodsType.SHOPPING_CARD.toString()),
										100) + " 张\n\r");
				sb.append("2. " + TextContents.TRANSPORT_CARD + ":\n\r");
				sb.append("\t\t20元\t"
						+ ServiceUtils
								.getGoodsService()
								.countCardNumGroupByTypeAndValue(
										Integer.parseInt(GoodsType.TRANSPORT_CARD.toString()),
										20) + " 张\n\r");
				sb.append("\t\t50元\t"
						+ ServiceUtils
								.getGoodsService()
								.countCardNumGroupByTypeAndValue(
										Integer.parseInt(GoodsType.TRANSPORT_CARD.toString()),
										50) + " 张\n\r");
				sb.append("\t\t100元\t"
						+ ServiceUtils
								.getGoodsService()
								.countCardNumGroupByTypeAndValue(
										Integer.parseInt(GoodsType.TRANSPORT_CARD.toString()),
										100) + " 张\n\r");
				sb.append("3. " + TextContents.TELEPHONE_CARD + ":\n\r");
				sb.append("\t\t20元\t"
						+ ServiceUtils
								.getGoodsService()
								.countCardNumGroupByTypeAndValue(
										Integer.parseInt(GoodsType.TELEPHONE_CARD.toString()),
										20) + " 张\n\r");
				sb.append("\t\t50元\t"
						+ ServiceUtils
								.getGoodsService()
								.countCardNumGroupByTypeAndValue(
										Integer.parseInt(GoodsType.TELEPHONE_CARD.toString()),
										50) + " 张\n\r");
				sb.append("\t\t100元\t"
						+ ServiceUtils
								.getGoodsService()
								.countCardNumGroupByTypeAndValue(
										Integer.parseInt(GoodsType.TELEPHONE_CARD.toString()),
										100) + " 张\n\r");
				return sb.toString();

			}  else if (eventKey.equalsIgnoreCase(EventKey.EXCHANGE.toString())) {
				StringBuffer sb = new StringBuffer();
				sb.append(TextContents.INTRODUCE_AWARD_INFO);
				sb.append("1. " + TextContents.SHOPPING_CARD + ":\n\r");
				sb.append("\t\t20元\t"
						+ ServiceUtils
								.getGoodsService()
								.countCardNumGroupByTypeAndValue(
										Integer.parseInt(GoodsType.SHOPPING_CARD
												.toString()), 20) + " 张\n\r");
				sb.append("\t\t50元\t"
						+ ServiceUtils
								.getGoodsService()
								.countCardNumGroupByTypeAndValue(
										Integer.parseInt(GoodsType.SHOPPING_CARD
												.toString()), 50) + " 张\n\r");
				sb.append("\t\t100元\t"
						+ ServiceUtils
								.getGoodsService()
								.countCardNumGroupByTypeAndValue(
										Integer.parseInt(GoodsType.SHOPPING_CARD
												.toString()), 100) + " 张\n\r");
				sb.append("2. " + TextContents.TRANSPORT_CARD + ":\n\r");
				sb.append("\t\t20元\t"
						+ ServiceUtils
								.getGoodsService()
								.countCardNumGroupByTypeAndValue(
										Integer.parseInt(GoodsType.TRANSPORT_CARD
												.toString()), 20) + " 张\n\r");
				sb.append("\t\t50元\t"
						+ ServiceUtils
								.getGoodsService()
								.countCardNumGroupByTypeAndValue(
										Integer.parseInt(GoodsType.TRANSPORT_CARD
												.toString()), 50) + " 张\n\r");
				sb.append("\t\t100元\t"
						+ ServiceUtils
								.getGoodsService()
								.countCardNumGroupByTypeAndValue(
										Integer.parseInt(GoodsType.TRANSPORT_CARD
												.toString()), 100) + " 张\n\r");
				sb.append("3. " + TextContents.TELEPHONE_CARD + ":\n\r");
				sb.append("\t\t20元\t"
						+ ServiceUtils
								.getGoodsService()
								.countCardNumGroupByTypeAndValue(
										Integer.parseInt(GoodsType.TELEPHONE_CARD
												.toString()), 20) + " 张\n\r");
				sb.append("\t\t50元\t"
						+ ServiceUtils
								.getGoodsService()
								.countCardNumGroupByTypeAndValue(
										Integer.parseInt(GoodsType.TELEPHONE_CARD
												.toString()), 50) + " 张\n\r");
				sb.append("\t\t100元\t"
						+ ServiceUtils
								.getGoodsService()
								.countCardNumGroupByTypeAndValue(
										Integer.parseInt(GoodsType.TELEPHONE_CARD
												.toString()), 100) + " 张\n\r");
				sb.append(TextContents.EXCHAGE_INFO.toString());
				return sb.toString();

			} else if (eventKey.equalsIgnoreCase(EventKey.EXCHANGE_QUERY
					.toString())) {
				StringBuffer sb = new StringBuffer();
				sb.append(TextContents.EXCHANGE_QUERY_INFO);
				List<ExchangeRecord> exchangeRecords = ServiceUtils
						.getExchangeRecordService().findExchangeRecordByOpenid(
								openid);
				if(exchangeRecords == null){
					return TextContents.EXCHANGE_QUERY_NULL.toString();
				}
				for (int i = 0; i < exchangeRecords.size(); i++) {
					sb.append("日期：" + exchangeRecords.get(i).getExchangeDate()
							+ "\t");
					int type = exchangeRecords.get(i).getType();
					String tmp = (type == 1) ? TextContents.SHOPPING_CARD
							.toString()
							: ((type == 2) ? TextContents.TRANSPORT_CARD
									.toString() : TextContents.TELEPHONE_CARD
									.toString());
					sb.append("卡类型：" + tmp + "\t");
					sb.append("卡面值：" + exchangeRecords.get(i).getValue() + "\t");
					sb.append("\n\r");
				}
				return sb.toString();
			} else if (eventKey.equalsIgnoreCase(EventKey.CREDITS_RANKING_LIST
					.toString())) {
				StringBuffer sb = new StringBuffer();
				sb.append(TextContents.CREDITS_RANKING_INFO);
				List<User> users = ServiceUtils.getUserService()
						.findOrderByCreditDesc();
				for (int i = 0; i < 3; i++) {
					sb.append("第" + (i + 1) + "名： " + users.get(i).getNickname()
							+ "\t" + users.get(i).getCredit() + "\n\r");
				}
				return sb.toString();
			} else if (eventKey.equalsIgnoreCase(EventKey.ADD_CREDITS
					.toString())) {
				long tmp = CommonUtils.changeCredits(openid, 100);
				return TextContents.Change_CREDITS_INFO.toString()+ "100。您目前的可用积分为：" + tmp + "。";
			} else if (eventKey
					.equalsIgnoreCase(EventKey.ADD_AWARDS.toString())) {
				/* 在插入之前最好判断下cardNum是否有重复，如果有则重新生成一个cardNum，此处为了简便，省略这个步骤 */
				Goods goods1 = new Goods();
				goods1.setType(Integer.valueOf(GoodsType.SHOPPING_CARD
						.toString()));
				goods1.setCardNum(CommonUtils.randomNumbers(5));
				goods1.setCardPwd(CommonUtils.randomNumbers(4));
				goods1.setValue(20);
				goods1.setId(Integer.toString(CommonUtils.randomNumbers(5)));
				ServiceUtils.getGoodsService().saveGoods(goods1);

				Goods goods2 = new Goods();
				goods2.setType(Integer.valueOf(GoodsType.SHOPPING_CARD
						.toString()));
				goods2.setCardNum(CommonUtils.randomNumbers(5));
				goods2.setCardPwd(CommonUtils.randomNumbers(4));
				goods2.setValue(50);
				goods2.setId(Integer.toString(CommonUtils.randomNumbers(5)));
				ServiceUtils.getGoodsService().saveGoods(goods2);

				Goods goods3 = new Goods();
				goods3.setType(Integer.valueOf(GoodsType.SHOPPING_CARD
						.toString()));
				goods3.setCardNum(CommonUtils.randomNumbers(5));
				goods3.setCardPwd(CommonUtils.randomNumbers(4));
				goods3.setValue(100);
				goods3.setId(Integer.toString(CommonUtils.randomNumbers(5)));
				ServiceUtils.getGoodsService().saveGoods(goods3);

				Goods goods4 = new Goods();
				goods4.setType(Integer.valueOf(GoodsType.TRANSPORT_CARD
						.toString()));
				goods4.setCardNum(CommonUtils.randomNumbers(5));
				goods4.setCardPwd(CommonUtils.randomNumbers(4));
				goods4.setValue(20);
				goods4.setId(Integer.toString(CommonUtils.randomNumbers(5)));
				ServiceUtils.getGoodsService().saveGoods(goods4);

				Goods goods5 = new Goods();
				goods5.setType(Integer.valueOf(GoodsType.TRANSPORT_CARD
						.toString()));
				goods5.setCardNum(CommonUtils.randomNumbers(5));
				goods5.setCardPwd(CommonUtils.randomNumbers(4));
				goods5.setValue(50);
				goods5.setId(Integer.toString(CommonUtils.randomNumbers(5)));
				ServiceUtils.getGoodsService().saveGoods(goods5);

				Goods goods6 = new Goods();
				goods6.setType(Integer.valueOf(GoodsType.TRANSPORT_CARD
						.toString()));
				goods6.setCardNum(CommonUtils.randomNumbers(5));
				goods6.setCardPwd(CommonUtils.randomNumbers(4));
				goods6.setValue(100);
				goods6.setId(Integer.toString(CommonUtils.randomNumbers(5)));
				ServiceUtils.getGoodsService().saveGoods(goods6);

				Goods goods7 = new Goods();
				goods7.setType(Integer.valueOf(GoodsType.TELEPHONE_CARD
						.toString()));
				goods7.setCardNum(CommonUtils.randomNumbers(5));
				goods7.setCardPwd(CommonUtils.randomNumbers(4));
				goods7.setValue(20);
				goods7.setId(Integer.toString(CommonUtils.randomNumbers(5)));
				ServiceUtils.getGoodsService().saveGoods(goods7);

				Goods goods8 = new Goods();
				goods8.setType(Integer.valueOf(GoodsType.TELEPHONE_CARD
						.toString()));
				goods8.setCardNum(CommonUtils.randomNumbers(5));
				goods8.setCardPwd(CommonUtils.randomNumbers(4));
				goods8.setValue(50);
				goods8.setId(Integer.toString(CommonUtils.randomNumbers(5)));
				ServiceUtils.getGoodsService().saveGoods(goods8);

				Goods goods9 = new Goods();
				goods9.setType(Integer.valueOf(GoodsType.TELEPHONE_CARD
						.toString()));
				goods9.setCardNum(CommonUtils.randomNumbers(5));
				goods9.setCardPwd(CommonUtils.randomNumbers(4));
				goods9.setValue(100);
				goods9.setId(Integer.toString(CommonUtils.randomNumbers(5)));
				ServiceUtils.getGoodsService().saveGoods(goods9);

				return TextContents.ADD_AWARDS.toString();
			}
		}
		if (eventType.equalsIgnoreCase(EventType.LOCATION.toString())) {
			UserTrace userTrace = new UserTrace();
			userTrace.setLatitude(Double.valueOf(CommonUtils.getRequestValue(
					datas, RequestKeys.LATITUDE)));
			userTrace.setLongitude(Double.valueOf(CommonUtils.getRequestValue(
					datas, RequestKeys.LONGITUDE)));
			userTrace.setOpenid(openid);
			userTrace.setPrec(Double.valueOf(CommonUtils.getRequestValue(
					datas, RequestKeys.PRECISION)));
			userTrace.setTime(new Date());
			ServiceUtils.getUserTraceService().saveUserTrace(userTrace);
		}
		return null;
	}

	private String checkEvent(OriginalEvent event) {
		List<String> lackItems = new ArrayList<String>();
		if (0 == event.getCategory() && 0 == event.getType()
				&& 0 == event.getDistrict() && 0 == event.getBusinessCircle()) {
			lackItems.add(TextContents.BASICINFO.toString());
		}
		if (0 == event.getLatitude() || 0 == event.getLongitude()) {
			lackItems.add(TextContents.LOCATION.toString());
		}
		if (null == event.getImageIds()) {
			lackItems.add(TextContents.IMAGE.toString());
		}

		if (null == event.getImageIds()) {
			lackItems.add(TextContents.IMAGE.toString());
		}
		if (null == event.getVoiceIds()) {
			lackItems.add(TextContents.VOICE.toString());
		}
		if (null == event.getDescriptions()) {
			lackItems.add(TextContents.DESCRIPTIONS.toString());
		}
		String result = combineString(",", lackItems);
		return "".equals(result) ? null : result;
	}

	private String combineString(String split, List<String> strings) {
		if (null == strings || strings.size() <= 0) {
			return "";
		}
		StringBuilder sb = new StringBuilder(strings.get(0));
		for (int i = 1; i < strings.size(); i++) {
			sb.append(split).append(strings.get(i));
		}
		return sb.toString();
	}
}
