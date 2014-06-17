/**
 * 
 */
package com.jm.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

import com.jm.constants.EventType;
import com.jm.constants.request.RequestKeys;

/**
 * @author LuZheqi
 * 
 */
public class CommonUtils {
	private static Properties properties;

	static {
		setProperties();
	}

	public static String getRequestValue(Map<String, String> datas,
			RequestKeys key) {
		return datas.get(key.toString());
	}

	public static String getEventType(Map<String, String> datas, EventType type) {
		return datas.get(type.toString());
	}

	public static String getProperties(String key) {
		return properties.getProperty(key);
	}

	public static String getProperties(String key, String defaultValue) {
		return properties.getProperty(key, defaultValue);
	}

	private static void setProperties() {
		try {
			InputStream in = CommonUtils.class
					.getResourceAsStream("/app.properties");
			properties = new Properties();
			properties.load(in);
			in.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
