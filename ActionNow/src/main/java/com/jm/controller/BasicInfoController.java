/**
 * 
 */
package com.jm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author LuZheqi
 * 
 */

@Controller
public class BasicInfoController {

	@RequestMapping(value = "basicinfo", method = RequestMethod.POST)
	@ResponseBody
	public String basic(String openid, int category, int type, int district,
			int businesscircle, String address, double longitude,
			double latitude) {
		return "";
	}

}
