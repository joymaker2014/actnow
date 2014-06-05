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

	public void handle(Map<String, String> datas) {
		String eventType = CommonUtils
				.getRequestValue(datas, RequestKeys.EVENT);
		if (eventType.equalsIgnoreCase(EventType.SUBSCRIBE.toString())) {
			String openid = datas.get(RequestKeys.FROMUSERNAME);
			List<User> users = ServiceUtils.getUserservice()
					.findUserOrderByNicknameDesc();
			User user = new User();
			user.setOpenid(openid);
			user.setNickname("user1");
			user.setAccount(0);
			user.setBlackNum(0);
			user.setCity("北京市");
			user.setCountry("朝阳区");
			user.setCredit(0);
			user.setHeadImageUrl(null);
			user.setProvince("北京市");
			user.setSex(Sex.MALE);
			user.setSubscribe(true);
			user.setSubscribeTime(new Date());
			if (null == users || users.isEmpty()) {
				user.setNickname("user1");
			} else {
				String maxUserName = users.get(0).getNickname();
				String maxSuffix = maxUserName.substring(4) + 1;
				user.setNickname("user" + maxSuffix);
			}
			ServiceUtils.getUserservice().saveUser(user);
		}
	}
}
