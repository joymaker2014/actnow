/**
 * 
 */
package com.jm.service.spi;

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
}
