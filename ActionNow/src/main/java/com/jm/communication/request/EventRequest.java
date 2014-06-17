/**
 * 
 */
package com.jm.communication.request;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.jm.constants.EventType;
import com.jm.constants.request.RequestKeys;
import com.jm.model.User;
import com.jm.model.codes.Sex;
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
				List<User> users = ServiceUtils.getUserservice()
						.findUserOrderByNicknameDesc();
				existUser = new User();
				existUser.setOpenid(openid);
				existUser.setNickname("user1");
				existUser.setAccount(0);
				existUser.setBlackNum(0);
				existUser.setCity("北京市");
				existUser.setCountry("朝阳区");
				existUser.setCredit(0);
				existUser.setHeadImageUrl(null);
				existUser.setProvince("北京市");
				existUser.setSex(Sex.MALE);
				existUser.setSubscribe(true);
				existUser.setSubscribeTime(new Date());
				if (null == users || users.isEmpty()) {
					existUser.setNickname("user1");
				} else {
					String maxUserName = users.get(0).getNickname();
					String maxSuffix = maxUserName.substring(4) + 1;
					existUser.setNickname("user" + maxSuffix);
				}
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
