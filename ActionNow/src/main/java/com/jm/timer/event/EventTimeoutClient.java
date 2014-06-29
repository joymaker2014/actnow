/**
 * 
 */
package com.jm.timer.event;

import java.util.Date;

import com.jm.cache.manager.EventCacheManager;
import com.jm.model.OriginalEvent;
import com.jm.timer.TimeoutClient;
import com.jm.util.ServiceUtils;

/**
 * @author LuZheqi
 * 
 */
public class EventTimeoutClient implements TimeoutClient {
	private final String _openid;
	private final String _eventid;

	public EventTimeoutClient(String openid, String eventid) {
		_openid = openid;
		_eventid = eventid;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jm.timer.TimeoutClient#scheduleTimeout(long)
	 */
	@Override
	public void scheduleTimeout(long paramLong) {
		OriginalEvent event = EventCacheManager.getInstance().getCache()
				.get(_openid);
		if (null == event || !event.getId().equals(_eventid)) {
			return;
		}
		event.setTime(new Date());
		ServiceUtils.getOriginalEventService().saveOriginalEvent(event);
		EventCacheManager.getInstance().getCache().invalidate(_openid);
	}

}
