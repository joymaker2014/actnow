/**
 * 
 */
package com.jm.communication.session;

import java.util.Map;

import com.jm.communication.request.EventRequest;
import com.jm.communication.response.EventResponse;

/**
 * @author LuZheqi
 * 
 */
public class EventSession {
	EventRequest eventRequest = new EventRequest();
	EventResponse eventResponse = new EventResponse();
	private final Map<String, String> _datas;

	public EventSession(Map<String, String> datas) {
		this._datas = datas;
	}

	public String execute() {
		String result = eventRequest.handle(_datas);
		return eventResponse.createResponse(result, _datas);
	}

}
