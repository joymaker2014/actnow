/**
 * 
 */
package com.jm.communication.session;

import java.util.Map;

import com.jm.communication.request.TextRequest;
import com.jm.communication.response.TextResponse;
import com.jm.constants.EventStatus;
import com.jm.constants.request.RequestKeys;
import com.jm.constants.request.TextContents;
import com.jm.util.CommonUtils;

/**
 * @author LuZheqi
 * 
 */
public class TextSession {
	TextRequest textRequest = new TextRequest();
	TextResponse textResponse = new TextResponse();
	final Map<String, String> _datas;

	public TextSession(Map<String, String> datas) {
		_datas = datas;
	}

	public String execute() {
		String toUserName = _datas.get(RequestKeys.TOUSERNAME.toString());
		String fromUserName = _datas.get(RequestKeys.FROMUSERNAME.toString());
		String status = textRequest.handle(_datas);
		String text = null;
		if (null == status) {
			text = TextContents.MENU_ROOT.toString();
		} else if (status.equals(EventStatus.STARTTING.toString())) {
			StringBuilder sb = new StringBuilder(
					TextContents.SUBMIT_BASIC.toString());
			sb.append(" ");
			sb.append("<a href=\"");
			sb.append(CommonUtils.getProperties("url.base",
					"http://127.0.0.1:8080/"));
			sb.append("submit.jsp?openid=" + fromUserName + "\">点击这里</a>");
			text = sb.toString();
		} else if (status.equalsIgnoreCase(TextContents.EVENT_END.toString())) {
			text = status;
		}
		return textResponse.createResponse(fromUserName, toUserName, text);
	}
}
