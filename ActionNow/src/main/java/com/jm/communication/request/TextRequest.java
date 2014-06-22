/**
 * 
 */
package com.jm.communication.request;

import java.util.ArrayList;
import java.util.Map;

import com.jm.cache.manager.EventCacheManager;
import com.jm.constants.EventStatus;
import com.jm.constants.request.RequestKeys;
import com.jm.constants.request.TextContents;
import com.jm.model.OriginalEvent;

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
		if (null == event) {
			return TextContents.WELCOME.toString();
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
