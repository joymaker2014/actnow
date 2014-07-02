/**
 * 
 */
package com.jm.communication.request;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.jm.cache.manager.EventCacheManager;
import com.jm.constants.EventStatus;
import com.jm.constants.GoodsType;
import com.jm.constants.request.RequestKeys;
import com.jm.constants.request.TextContents;
import com.jm.model.ExchangeRecord;
import com.jm.model.Goods;
import com.jm.model.OriginalEvent;
import com.jm.util.CommonUtils;
import com.jm.util.ServiceUtils;

/**
 * @author LuZheqi
 * 
 */
public class TextRequest {
	public String handle(Map<String, String> datas) {
		String openid = datas.get(RequestKeys.FROMUSERNAME.toString());
		OriginalEvent event = EventCacheManager.getInstance().getCache()
				.get(openid);
		String content = datas.get(RequestKeys.CONTENT.toString());
		String tmp = content.trim();
		if (tmp.startsWith("兑换")) {
			if (tmp.contains(TextContents.SHOPPING_CARD_MINI.toString())) {
				/* get the number of the card */
				int value = Integer.parseInt(tmp.substring(tmp.indexOf("兑换"),
						tmp.indexOf("元")));
				if ((value != 20) || (value != 50) || (value != 100)) {
					return TextContents.EXCHANGE_VALUE_WRONG.toString();
				}
				int index1 = tmp.indexOf(TextContents.SHOPPING_CARD_MINI
						.toString());
				int index2 = tmp.indexOf("张");
				String tmp2 = tmp.substring(index1 + 3, index2);
				tmp2 = tmp2.trim();
				int exchangeNum = Integer.parseInt(tmp2);

				/* db operation */
				CommonUtils.changeCredits(openid, (-1) * value);
				List<String> cardNums = ServiceUtils.getGoodsService()
						.selectCardsByTypeAndValue(
								Integer.parseInt(GoodsType.SHOPPING_CARD
										.toString()), value);
				if (cardNums.size() < exchangeNum) {
					String result = TextContents.EXCHANGE_NUMBER_WRONG
							.toString()
							+ "目前只有"
							+ cardNums.size()
							+ "张"
							+ TextContents.SHOPPING_CARD.toString();
					return result;
				}
				StringBuffer sb = new StringBuffer();
				sb.append(TextContents.EXCHANGE_OK.toString() + exchangeNum
						+ "张" + TextContents.SHOPPING_CARD.toString()
						+ ",卡号和密码如下：\n\r");
				for (int i = 0; i < exchangeNum; i++) {
					Goods goods = ServiceUtils.getGoodsService()
							.selectCoodsByCardNum(
									Integer.parseInt(cardNums.get(i)));
					sb.append("卡号：" + goods.getCardNum() + "密码："
							+ goods.getCardPwd() + "\n\r");
					ExchangeRecord exchangeRecord = new ExchangeRecord();
					exchangeRecord.setOpenid(openid);
					exchangeRecord.setCardNum(goods.getCardNum());
					exchangeRecord.setCardPwd(goods.getCardPwd());
					exchangeRecord.setExchangeDate((new Date()).toString());
					exchangeRecord.setType(goods.getType());
					exchangeRecord.setValue(goods.getValue());
					ServiceUtils.getExchangeRecordService().saveExchangeRecord(exchangeRecord);
					ServiceUtils.getGoodsService().deleteCoodsByCardNum(Integer.parseInt(cardNums.get(i)));
				}
				return sb.toString();
			}
			if (tmp.contains(TextContents.TRANSPORT_CARD_MINI.toString())) {
				int value = Integer.parseInt(tmp.substring(tmp.indexOf("兑换"),
						tmp.indexOf("元")));
				int index1 = tmp.indexOf(TextContents.TRANSPORT_CARD_MINI
						.toString());
				int index2 = tmp.indexOf("张");
				String tmp2 = tmp.substring(index1 + 3, index2);
				tmp2 = tmp2.trim();			
				int exchangeNum = Integer.parseInt(tmp2);

				/* db operation */
				CommonUtils.changeCredits(openid, (-1) * value);
				List<String> cardNums = ServiceUtils.getGoodsService()
						.selectCardsByTypeAndValue(
								Integer.parseInt(GoodsType.TRANSPORT_CARD
										.toString()), value);
				if (cardNums.size() < exchangeNum) {
					String result = TextContents.EXCHANGE_NUMBER_WRONG
							.toString()
							+ "目前只有"
							+ cardNums.size()
							+ "张"
							+ TextContents.TRANSPORT_CARD.toString();
					return result;
				}
				StringBuffer sb = new StringBuffer();
				sb.append(TextContents.EXCHANGE_OK.toString() + exchangeNum
						+ "张" + TextContents.TRANSPORT_CARD.toString()
						+ ",卡号和密码如下：\n\r");
				for (int i = 0; i < exchangeNum; i++) {
					Goods goods = ServiceUtils.getGoodsService()
							.selectCoodsByCardNum(
									Integer.parseInt(cardNums.get(i)));
					sb.append("卡号：" + goods.getCardNum() + "密码："
							+ goods.getCardPwd() + "\n\r");
					ExchangeRecord exchangeRecord = new ExchangeRecord();
					exchangeRecord.setOpenid(openid);
					exchangeRecord.setCardNum(goods.getCardNum());
					exchangeRecord.setCardPwd(goods.getCardPwd());
					exchangeRecord.setExchangeDate((new Date()).toString());
					exchangeRecord.setType(goods.getType());
					exchangeRecord.setValue(goods.getValue());
					ServiceUtils.getExchangeRecordService().saveExchangeRecord(exchangeRecord);
					ServiceUtils.getGoodsService().deleteCoodsByCardNum(Integer.parseInt(cardNums.get(i)));
				}
				return sb.toString();
			}
			if (tmp.contains(TextContents.TELEPHONE_CARD_MINI.toString())) {
				int value = Integer.parseInt(tmp.substring(tmp.indexOf("兑换"),
						tmp.indexOf("元")));
				int index1 = tmp.indexOf(TextContents.TELEPHONE_CARD_MINI
						.toString());
				int index2 = tmp.indexOf("张");
				String tmp2 = tmp.substring(index1 + 3, index2);
				tmp2 = tmp2.trim();
				
				int exchangeNum = Integer.parseInt(tmp2);

				/* db operation */
				CommonUtils.changeCredits(openid, (-1) * value);
				List<String> cardNums = ServiceUtils.getGoodsService()
						.selectCardsByTypeAndValue(
								Integer.parseInt(GoodsType.TELEPHONE_CARD
										.toString()), value);
				if (cardNums.size() < exchangeNum) {
					String result = TextContents.EXCHANGE_NUMBER_WRONG
							.toString()
							+ "目前只有"
							+ cardNums.size()
							+ "张"
							+ TextContents.TELEPHONE_CARD.toString();
					return result;
				}
				StringBuffer sb = new StringBuffer();
				sb.append(TextContents.EXCHANGE_OK.toString() + exchangeNum
						+ "张" + TextContents.TELEPHONE_CARD.toString()
						+ ",卡号和密码如下：\n\r");
				for (int i = 0; i < exchangeNum; i++) {
					Goods goods = ServiceUtils.getGoodsService()
							.selectCoodsByCardNum(
									Integer.parseInt(cardNums.get(i)));
					sb.append("卡号：" + goods.getCardNum() + "密码："
							+ goods.getCardPwd() + "\n\r");
					ExchangeRecord exchangeRecord = new ExchangeRecord();
					exchangeRecord.setOpenid(openid);
					exchangeRecord.setCardNum(goods.getCardNum());
					exchangeRecord.setCardPwd(goods.getCardPwd());
					exchangeRecord.setExchangeDate((new Date()).toString());
					exchangeRecord.setType(goods.getType());
					exchangeRecord.setValue(goods.getValue());
					ServiceUtils.getExchangeRecordService().saveExchangeRecord(exchangeRecord);
					ServiceUtils.getGoodsService().deleteCoodsByCardNum(Integer.parseInt(cardNums.get(i)));
				}
				return sb.toString();
			}
		}
		if (null == event) {
			return TextContents.WELCOME.toString();
		} else if (event.getStatus().equals(EventStatus.STARTTING)) {
			return EventStatus.STARTTING.toString();
		} else if (event.getStatus().equals(EventStatus.BASICOK)
				|| event.getStatus().equals(EventStatus.ENDDING)) {
			ArrayList<String> descriptions = event.getDescriptions();
			if (null == descriptions) {
				descriptions = new ArrayList<String>();
			}
			event.setDescriptions(descriptions);
			descriptions.add(content);
			if (event.getStatus().equals(EventStatus.ENDDING)) {
				event.setStatus(EventStatus.BASICOK);
			}
			return TextContents.RECEIVE_OK.toString();
		}

		return null;
	}
}
