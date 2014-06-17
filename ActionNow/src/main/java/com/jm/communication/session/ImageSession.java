/**
 * 
 */
package com.jm.communication.session;

import java.util.Map;

import com.jm.communication.request.ImageRequest;
import com.jm.communication.response.ImageResponse;

/**
 * @author LuZheqi
 * 
 */
public class ImageSession {
	ImageRequest imageRequest = new ImageRequest();
	ImageResponse imageResponse = new ImageResponse();
	final Map<String, String> _datas;

	public ImageSession(Map<String, String> datas) {
		_datas = datas;
	}

	public String execute() {
		String status = imageRequest.handle(_datas);
		return imageResponse.createResponse(status, _datas);
	}
}
