/**
 * 
 */
package com.jm.communication.session;

import java.util.Map;

import com.jm.communication.request.EventRequest;
import com.jm.communication.response.EventResponse;
import com.jm.communication.response.NullResponse;
import com.jm.constants.request.RequestKeys;

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
		String toUserName = _datas.get(RequestKeys.TOUSERNAME.toString());
		String fromUserName = _datas.get(RequestKeys.FROMUSERNAME.toString());
		eventRequest.handle(_datas);
		return NullResponse.createNullTextResponse(fromUserName, toUserName);
	}

}
