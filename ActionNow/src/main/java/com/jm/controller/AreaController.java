/**
 * 
 */
package com.jm.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jm.constants.AddressConstants;
import com.jm.model.address.City;
import com.jm.model.address.County;
import com.jm.model.address.Province;

/**
 * @author Fulh
 * 
 */
@Controller
@RequestMapping("/area/*")
public class AreaController {

	/**
	 * @return
	 */
	@RequestMapping(value = "provinces", method = RequestMethod.GET)
	@ResponseBody
	public Collection<Province> getProvinces() {
		return AddressConstants.provinces.values();
	}

	/**
	 * @param provinceid
	 * @return
	 */
	@RequestMapping(value = "cities", method = RequestMethod.GET)
	public @ResponseBody
	List<City> getCities(int provinceid) {
		List<Integer> cityIndexs = AddressConstants.pc.get(provinceid);
		List<City> result = new ArrayList<City>();
		for (int cityIndex : cityIndexs) {
			result.add(AddressConstants.cities.get(cityIndex));
		}
		return result;
	}

	/**
	 * @param cityid
	 * @return
	 */
	@RequestMapping(value = "counties", method = RequestMethod.GET)
	public @ResponseBody
	List<County> getCounties(int cityid) {
		List<Integer> countyIndexs = AddressConstants.cc.get(cityid);
		List<County> result = new ArrayList<County>();
		for (int countyIndex : countyIndexs) {
			result.add(AddressConstants.counties.get(countyIndex));
		}
		return result;
	}
}
