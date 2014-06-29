/**
 * 
 */
package com.jm.communication.request;

import java.util.ArrayList;
import java.util.Map;

import com.jm.cache.manager.EventCacheManager;
import com.jm.constants.CommonConstants;
import com.jm.constants.EventStatus;
import com.jm.constants.MediaType;
import com.jm.constants.request.RequestKeys;
import com.jm.constants.request.TextContents;
import com.jm.constants.response.ResponseKeys;
import com.jm.model.OriginalEvent;
import com.jm.timer.OneTimeTrigger;
import com.jm.timer.download.DownloadMediaTimerTask;

/**
 * @author LuZheqi
 * 
 */
public class VoiceRequest {
	public String handle(Map<String, String> datas) {
		String openid = datas.get(RequestKeys.FROMUSERNAME.toString());
		OriginalEvent event = EventCacheManager.getInstance().getCache()
				.get(openid);
		if (null != event) {
			if (event.getStatus().equals(EventStatus.STARTTING)) {
				return EventStatus.STARTTING.toString();
			} else if (event.getStatus().equals(EventStatus.BASICOK)) {
				ArrayList<String> details = event.getDetails();
				if (null == details) {
					details = new ArrayList<String>();
				}
				event.setDetails(details);
				details.add(datas.get(ResponseKeys.MEDIAID));
				DownloadMediaTimerTask dlTask = new DownloadMediaTimerTask(
						new OneTimeTrigger(10), event.getCategory(),
						event.getType(), MediaType.VOICE, event.getTime(),
						datas.get(ResponseKeys.MEDIAID.toString()));
				CommonConstants.downloadTimer.schedule(dlTask);
				return TextContents.RECEIVE_OK.toString();
			}
		}

		return null;
	}
}
