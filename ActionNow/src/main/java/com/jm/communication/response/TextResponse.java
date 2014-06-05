/**
 * 
 */
package com.jm.communication.response;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.jdom2.Element;
import org.jdom2.output.XMLOutputter;

import com.jm.constants.response.ResponseKeys;

/**
 * @author LuZheqi
 * 
 */
public class TextResponse {

	public String createResponse(String fromName, String toName) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String times = format.format(new Date());

		Element rootXML = new Element("xml");
		rootXML.addContent(new Element(ResponseKeys.TOUSERNAME.toString())
				.setText(fromName));
		rootXML.addContent(new Element(ResponseKeys.FROMUSERNAME.toString())
				.setText(toName));
		rootXML.addContent(new Element(ResponseKeys.CREATETIME.toString())
				.setText(times));
		rootXML.addContent(new Element(ResponseKeys.MSGTYPE.toString())
				.setText("text"));
		rootXML.addContent(new Element(ResponseKeys.CONTENT.toString())
				.setText("开发中，敬请期待！"));

		XMLOutputter XMLOut = new XMLOutputter();
		return XMLOut.outputString(rootXML);
	}
}
