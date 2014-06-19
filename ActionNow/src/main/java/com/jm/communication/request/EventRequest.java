/**
 * 
 */
package com.jm.communication.request;

import java.util.Date;
import java.util.Map;

import com.jm.client.WeixinClient;
import com.jm.constants.EventType;
import com.jm.constants.request.RequestKeys;
import com.jm.model.User;
import com.jm.util.CommonUtils;
import com.jm.util.ServiceUtils;

/**
 * @author LuZheqi
 * 
 */
public class EventRequest {

	public String handle(Map<String, String> datas) {
		String eventType = CommonUtils
				.getRequestValue(datas, RequestKeys.EVENT);
		String openid = datas.get(RequestKeys.FROMUSERNAME.toString());
		User existUser = ServiceUtils.getUserservice().findUserById(openid);
		if (eventType.equalsIgnoreCase(EventType.SUBSCRIBE.toString())) {
			if (null != existUser) {
				existUser.setSubscribe(true);
				existUser.setSubscribeTime(new Date());
			} else {
				existUser = WeixinClient.getUserInfo(openid);
			}
			ServiceUtils.getUserservice().saveUser(existUser);
			return EventType.SUBSCRIBE.toString();
		} else if (eventType.equalsIgnoreCase(EventType.UNSUBSCRIBE.toString())) {
			if (null != existUser) {
				existUser.setSubscribe(false);
				existUser.setSubscribeTime(new Date());
				ServiceUtils.getUserservice().saveUser(existUser);
			}
			return EventType.UNSUBSCRIBE.toString();
		}
		return null;
	}
}
