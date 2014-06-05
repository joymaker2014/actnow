/**
 * 
 */
package com.jm.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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

	/**
	 * @return the Userservice
	 */
	public static UserService getUserservice() {
		return _userService;
	}
}
