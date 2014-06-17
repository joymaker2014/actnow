/**
 * 
 */
package com.jm.communication.session;

import java.util.Map;

import com.jm.communication.request.VoiceRequest;
import com.jm.communication.response.VoiceResponse;

/**
 * @author LuZheqi
 * 
 */
public class VoiceSession {
	VoiceRequest voiceRequest = new VoiceRequest();
	VoiceResponse voiceResponse = new VoiceResponse();
	final Map<String, String> _datas;

	public VoiceSession(Map<String, String> datas) {
		_datas = datas;
	}

	public String execute() {
		String status = voiceRequest.handle(_datas);
		return voiceResponse.createResponse(status, _datas);
	}
}
