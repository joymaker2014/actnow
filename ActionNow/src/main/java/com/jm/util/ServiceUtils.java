/**
 * 
 */
package com.jm.util;

import com.jm.service.spi.OriginalEventService;
import com.jm.service.spi.UserService;

/**
 * @author LuZheqi
 * 
 */
public class ServiceUtils {
	private static UserService userService;
	private static OriginalEventService originalEventService;

	/**
	 * @return the Userservice
	 */
	public static UserService getUserService() {
		return userService;
	}
	/**
	 * @return the Originaleventservice
	 */
	public static OriginalEventService getOriginalEventService() {
		return originalEventService;
	}

	public static void setUserService(UserService userService) {
		ServiceUtils.userService = userService;
	}

	public static void setOriginalEventService(
			OriginalEventService originalEventService) {
		ServiceUtils.originalEventService = originalEventService;
	}

}
