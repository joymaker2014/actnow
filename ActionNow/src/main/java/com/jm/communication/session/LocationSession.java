/**
 * 
 */
package com.jm.communication.session;

import java.util.Map;

import com.jm.communication.request.LocationRequest;
import com.jm.communication.response.LocationResponse;

/**
 * @author LuZheqi
 * 
 */
public class LocationSession {
	LocationRequest locationRequest = new LocationRequest();
	LocationResponse locationResponse = new LocationResponse();
	final Map<String, String> _datas;

	public LocationSession(Map<String, String> datas) {
		_datas = datas;
	}

	public String execute() {
		String status = locationRequest.handle(_datas);
		return locationResponse.createResponse(status, _datas);
	}
}
