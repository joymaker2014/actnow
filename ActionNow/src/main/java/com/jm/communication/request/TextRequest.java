/**
 * 
 */
package com.jm.communication.request;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import com.jm.cache.manager.EventCacheManager;
import com.jm.constants.EventStatus;
import com.jm.constants.request.MenuItems;
import com.jm.constants.request.RequestKeys;
import com.jm.constants.request.TextContents;
import com.jm.model.OriginalEvent;
import com.jm.timer.event.EventTimeoutClient;
import com.jm.timer.event.EventTimeoutTask;
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
		if (null == event || event.getStatus().equals(EventStatus.GPSOK)) {
			if (content.equalsIgnoreCase(MenuItems.ROOT_START_EVENT.toString())) {
				OriginalEvent oevent = new OriginalEvent();
				oevent.setId(UUID.randomUUID().toString());
				oevent.setStatus(EventStatus.STARTTING);
				oevent.setUser(ServiceUtils.getUserservice().findUserById(
						openid));
				EventCacheManager.getInstance().getCache().put(openid, oevent);
				new EventTimeoutTask(new EventTimeoutClient(openid,
						oevent.getId()));
				return EventStatus.STARTTING.toString();
			}
		} else if (null != event
				&& content
						.equalsIgnoreCase(MenuItems.ROOT_END_EVENT.toString())) {
			event.setTime(new Date());
			ServiceUtils.getOriginaleventservice().saveOriginalEvent(event);
			EventCacheManager.getInstance().getCache().invalidate(openid);
			return TextContents.EVENT_END.toString();

		} else if (event.getStatus().equals(EventStatus.STARTTING)) {
			return EventStatus.STARTTING.toString();
		} else if (event.getStatus().equals(EventStatus.BASICOK)) {
			ArrayList<String> details = event.getDetails();
			if (null == details) {
				details = new ArrayList<String>();
			}
			event.setDetails(details);
			details.add(content);
			return TextContents.RECEIVE_OK.toString();
		}

		return null;
	}
}
