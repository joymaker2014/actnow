/**
 * 
 */
package com.jm.communication.request;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

import com.jm.cache.manager.EventCacheManager;
import com.jm.client.WeixinClient;
import com.jm.constants.EventKey;
import com.jm.constants.EventStatus;
import com.jm.constants.EventType;
import com.jm.constants.request.RequestKeys;
import com.jm.constants.request.TextContents;
import com.jm.model.OriginalEvent;
import com.jm.model.User;
import com.jm.timer.event.EventTimeoutClient;
import com.jm.timer.event.EventTimeoutTask;
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
					oevent.setUser(ServiceUtils.getUserservice().findUserById(
							openid));
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
					return TextContents.PLAESE_END_EVENT.toString();
				} else {
					event.setTime(new Date());
					ServiceUtils.getOriginaleventservice().saveOriginalEvent(
							event);
					EventCacheManager.getInstance().getCache()
							.invalidate(openid);
					return TextContents.EVENT_END.toString();
				}

			} else if (eventKey.equalsIgnoreCase(EventKey.ROOT_QUERY_EVENT
					.toString())) {
				return TextContents.DEVELOPING.toString();
			}
		}
		return null;
	}
}
