/**
 * 
 */
package com.jm.communication.response;

import java.util.Map;

import com.jm.constants.EventStatus;
import com.jm.constants.request.RequestKeys;
import com.jm.constants.request.TextContents;
import com.jm.util.CommonUtils;
import com.jm.util.ResponseUtils;

/**
 * @author LuZheqi
 * 
 */
public class LocationResponse {

	public String createResponse(String result, Map<String, String> datas) {
		String toUserName = datas.get(RequestKeys.TOUSERNAME.toString());
		String fromUserName = datas.get(RequestKeys.FROMUSERNAME.toString());
		String text = null;
		if (null == result) {
			text = TextContents.MENU_ROOT.toString();
		} else if (result.equals(EventStatus.STARTTING.toString())) {
			StringBuilder sb = new StringBuilder(
					TextContents.SUBMIT_BASIC.toString());
			sb.append(" ");
			sb.append("<a href=\"");
			sb.append(CommonUtils.getProperties("url.base",
					"http://127.0.0.1:8080/"));
			sb.append("submit.jsp?openid=" + fromUserName + "\">点击这里</a>");
			text = sb.toString();
		} else if (result.equalsIgnoreCase(TextContents.RECEIVE_OK.toString())) {
			text = result;
		}
		return ResponseUtils.createTextResponse(fromUserName, toUserName, text);
	}
}
