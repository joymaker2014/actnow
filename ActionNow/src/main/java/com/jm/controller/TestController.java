package com.jm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("test")
public class TestController {

	@RequestMapping(method = RequestMethod.POST)
	public String test(String openid,
			int category, 
			int type,
			int district,
			int businesscircle,
			String address,
			double longitude,
			double latitude) {
		return openid + " " 
				+ category + " " 
				+ type + " " 
				+ district + " " 
				+ businesscircle + " " 
				+ address + " " 
				+ longitude + " " 
				+ latitude + " " ;
	}
}
