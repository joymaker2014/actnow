/**
 * 
 */
package com.jm.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jm.service.spi.OriginalEventService;
import com.jm.service.spi.UserService;

/**
 * @author LuZheqi
 * 
 */
public class ServiceUtils {
	private static final ApplicationContext ctx = new ClassPathXmlApplicationContext(
			"/META-INF/spring/app-context.xml");
	private static final UserService _userService = ctx.getBean("userService",
			UserService.class);
	private static final OriginalEventService _originalEventService = ctx
			.getBean("originalEventService", OriginalEventService.class);

	/**
	 * @return the Userservice
	 */
	public static UserService getUserservice() {
		return _userService;
	}

	/**
	 * @return the Originaleventservice
	 */
	public static OriginalEventService getOriginaleventservice() {
		return _originalEventService;
	}

}
