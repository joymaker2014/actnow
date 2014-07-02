/**
 * 
 */
package com.jm.util;

import java.util.Map;

import com.jm.constants.request.RequestKeys;

/**
 * @author LuZheqi
 * 
 */
public class RequestUtils {
	public String getMsgType(Map<String, String> datas) {
		return CommonUtils.getRequestValue(datas, RequestKeys.MSGTYPE);
	}

	public String getEventType(Map<String, String> datas) {
		return CommonUtils.getRequestValue(datas, RequestKeys.EVENT);
	}

}
