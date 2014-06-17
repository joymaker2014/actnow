/**
 * 
 */
package com.jm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jm.cache.manager.EventCacheManager;
import com.jm.constants.EventStatus;
import com.jm.model.OriginalEvent;

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
		OriginalEvent event = EventCacheManager.getInstance().getCache()
				.get(openid);
		if (null != event) {
			event.setCategory(category);
			event.setType(type);
			event.setDistrict(district);
			event.setBusinessCircle(businesscircle);
			event.setAddress(address);
			event.setStatus(EventStatus.BASICOK);
		}
		return "success";
	}

}
