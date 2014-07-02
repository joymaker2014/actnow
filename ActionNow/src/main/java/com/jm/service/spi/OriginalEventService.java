/**
 * 
 */
package com.jm.service.spi;

import java.util.List;

import com.jm.model.OriginalEvent;

/**
 * @author LuZheqi
 * 
 */
public interface OriginalEventService {

	void saveOriginalEvent(OriginalEvent originalEvent);

	OriginalEvent findOriginalEventById(String id);

	void updateOriginalEvent(OriginalEvent originalEvent);

	void deleteOriginalEventById(String id);

	List<OriginalEvent> getSimilarEvents(int category, int type, int district,
			int businessCircle);

	List<OriginalEvent> getEvents(String openid);
}
