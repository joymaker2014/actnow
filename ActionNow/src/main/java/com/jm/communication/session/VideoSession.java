/**
 * 
 */
package com.jm.communication.session;

import java.util.Map;

import com.jm.communication.request.VideoRequest;
import com.jm.communication.response.VideoResponse;

/**
 * @author LuZheqi
 * 
 */
public class VideoSession {
	VideoRequest videoRequest = new VideoRequest();
	VideoResponse videoResponse = new VideoResponse();
	final Map<String, String> _datas;

	public VideoSession(Map<String, String> datas) {
		_datas = datas;
	}

	public String execute() {
		String status = videoRequest.handle(_datas);
		return videoResponse.createResponse(status, _datas);
	}
}
