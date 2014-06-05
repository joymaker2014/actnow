/**
 * 
 */
package com.jm.communication.session;

import java.util.Map;

import com.jm.communication.request.TextRequest;
import com.jm.communication.response.TextResponse;
import com.jm.constants.request.RequestKeys;

/**
 * @author LuZheqi
 * 
 */
public class TextSession {
	TextRequest textRequest = new TextRequest();
	TextResponse textResponse = new TextResponse();

	public String execute(Map<String, String> datas) {
		String toUserName = datas.get(RequestKeys.TOUSERNAME.toString());
		String fromUserName = datas.get(RequestKeys.FROMUSERNAME.toString());
		return textResponse.createResponse(fromUserName, toUserName);
	}
}
