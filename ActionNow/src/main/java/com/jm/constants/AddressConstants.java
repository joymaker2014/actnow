/**
 * 
 */
package com.jm.constants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import com.jm.model.address.City;
import com.jm.model.address.County;
import com.jm.model.address.Province;

/**
 * @author LuZheqi
 * 
 */
public class AddressConstants {
	public final static Map<Integer, Province> provinces = new HashMap<Integer, Province>();
	public final static Map<Integer, City> cities = new HashMap<Integer, City>();
	public final static Map<Integer, County> counties = new HashMap<Integer, County>();
	public final static Map<Integer, ArrayList<Integer>> pc = new HashMap<Integer, ArrayList<Integer>>();
	public final static Map<Integer, ArrayList<Integer>> cc = new HashMap<Integer, ArrayList<Integer>>();

	static {
		SAXBuilder sax = new SAXBuilder();
		try {
			Document doc = sax.build(AddressConstants.class.getClassLoader()
					.getResourceAsStream("/address.xml"));
			Element root = doc.getRootElement();
			List<Element> provinceElements = root.getChildren();
			for (Element provinceElement : provinceElements) {
				String pindex = provinceElement.getAttributeValue("index");
				String pname = provinceElement.getAttributeValue("name");
				provinces.put(Integer.valueOf(pindex),
						new Province(Integer.valueOf(pindex), pname));
				pc.put(Integer.valueOf(pindex), new ArrayList<Integer>());
				List<Element> cityElements = provinceElement.getChildren();
				for (Element cityElement : cityElements) {
					String cindex = pindex
							+ cityElement.getAttributeValue("index");
					String cname = cityElement.getAttributeValue("name");
					cities.put(Integer.valueOf(cindex),
							new City(Integer.valueOf(cindex), cname));
					ArrayList<Integer> pcValue = pc
							.get(Integer.valueOf(pindex));
					pcValue.add(Integer.valueOf(cindex));
					pc.put(Integer.valueOf(pindex), pcValue);
					cc.put(Integer.valueOf(cindex), new ArrayList<Integer>());

					List<Element> countyElements = cityElement.getChildren();
					for (Element countyElement : countyElements) {
						String c1index = cindex
								+ countyElement.getAttributeValue("index");
						String c1name = countyElement.getAttributeValue("name");
						counties.put(Integer.valueOf(c1index), new County(
								Integer.valueOf(c1index), c1name));

						ArrayList<Integer> ccValue = cc.get(Integer
								.valueOf(Integer.valueOf(cindex)));
						ccValue.add(Integer.valueOf(c1index));
						cc.put(Integer.valueOf(cindex), ccValue);
					}
				}
			}
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
