/**
 * 
 */
package com.jm.communication.session;

import java.util.Map;

import com.jm.communication.request.TextRequest;
import com.jm.communication.response.TextResponse;

/**
 * @author LuZheqi
 * 
 */
public class TextSession {
	TextRequest textRequest = new TextRequest();
	TextResponse textResponse = new TextResponse();
	final Map<String, String> _datas;

	public TextSession(Map<String, String> datas) {
		_datas = datas;
	}

	public String execute() {
		String status = textRequest.handle(_datas);
		return textResponse.createResponse(status, _datas);
	}
}
