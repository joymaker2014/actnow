/**
 * 
 */
package com.jm.communication.response;

import java.util.Map;

import com.jm.constants.EventType;
import com.jm.constants.request.RequestKeys;
import com.jm.constants.request.TextContents;
import com.jm.util.ResponseUtils;

/**
 * @author LuZheqi
 * 
 */
public class EventResponse {
	public String createResponse(String request, Map<String, String> datas) {
		String toUserName = datas.get(RequestKeys.TOUSERNAME.toString());
		String fromUserName = datas.get(RequestKeys.FROMUSERNAME.toString());
		String response = request;
		if (null == request) {
		} else if (EventType.SUBSCRIBE.toString().equalsIgnoreCase(request)) {
			return ResponseUtils.createTextResponse(fromUserName, toUserName,
					TextContents.WELCOME.toString());
		}
		return ResponseUtils.createTextResponse(fromUserName, toUserName,
				response);
	}
}
