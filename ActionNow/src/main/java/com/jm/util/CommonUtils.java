/**
 * 
 */
package com.jm.util;

import java.util.Map;

import com.jm.constants.EventType;
import com.jm.constants.request.RequestKeys;

/**
 * @author LuZheqi
 * 
 */
public class CommonUtils {
	public static String getRequestValue(Map<String, String> datas,
			RequestKeys key) {
		return datas.get(key.toString());
	}

	public static String getEventType(Map<String, String> datas, EventType type) {
		return datas.get(type.toString());
	}
}
