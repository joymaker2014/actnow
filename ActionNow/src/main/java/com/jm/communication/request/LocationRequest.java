/**
 * 
 */
package com.jm.communication.request;

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
public class LocationRequest {
	public String handle(Map<String, String> datas) {
		String openid = datas.get(RequestKeys.FROMUSERNAME.toString());
		OriginalEvent event = EventCacheManager.getInstance().getCache()
				.get(openid);
		if (null != event) {
			if (event.getStatus().equals(EventStatus.STARTTING)) {
				return EventStatus.STARTTING.toString();
			} else if (event.getStatus().equals(EventStatus.BASICOK)
					|| event.getStatus().equals(EventStatus.ENDDING)) {
				event.setLatitude(Double.valueOf(datas
						.get(RequestKeys.LOCATION_X.toString())));
				event.setLongitude(Double.valueOf(datas
						.get(RequestKeys.LOCATION_Y.toString())));
				if (event.getStatus().equals(EventStatus.ENDDING)) {
					event.setStatus(EventStatus.BASICOK);
				}
				return TextContents.RECEIVE_OK.toString();
			}
		}

		return null;
	}
}
