/**
 * 
 */
package com.jm.controller;

import java.io.IOException;
import java.io.StringReader;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xml.sax.InputSource;

import com.jm.communication.session.EventSession;
import com.jm.communication.session.ImageSession;
import com.jm.communication.session.LocationSession;
import com.jm.communication.session.TextSession;
import com.jm.communication.session.VoiceSession;
import com.jm.constants.CommonConstants;
import com.jm.constants.MessageType;
import com.jm.timer.user.UserCheckTimerTask;
import com.jm.util.ResponseUtils;

/**
 * @author LuZheqi
 * 
 */

@Controller
public class PipeController {
	private final static String TOKEN = "thisisactionnow";

	@RequestMapping(value = "pipe", method = RequestMethod.GET)
	@ResponseBody
	public String valid(String signature, String timestamp, String nonce,
			String echostr) throws NoSuchAlgorithmException {
		if (null == signature) {
			signature = "";
		}
		if (null == timestamp) {
			timestamp = "";
		}
		if (null == nonce) {
			nonce = "";
		}
		String[] ostrs = new String[] { TOKEN, timestamp, nonce };
		Arrays.sort(ostrs);
		String str = ostrs[0] + ostrs[1] + ostrs[2];
		byte[] bt = str.getBytes();

		if (DigestUtils.shaHex(bt).equals(signature)) {
			return echostr;
		}

		return "error";
	}

	@RequestMapping(value = "pipe", method = RequestMethod.POST)
	@ResponseBody
	public String pipe(@RequestBody String content) {
		try {
			StringReader read = new StringReader(content);
			InputSource source = new InputSource(read);
			SAXBuilder sax = new SAXBuilder();
			Document doc = sax.build(source);
			Element root = doc.getRootElement();// 指向根节点
			List<Element> elements = root.getChildren();
			Map<String, String> datas = new HashMap<String, String>();
			for (Element element : elements) {
				datas.put(element.getName(), element.getText());
			}

			String msgType = root.getChild("MsgType").getText();
			String toUserName = root.getChild("ToUserName").getText();
			String fromUserName = root.getChild("FromUserName").getText();
			CommonConstants.mixTimer.schedule(new UserCheckTimerTask(
					fromUserName));
			String responseStr = ResponseUtils.createTextResponse(fromUserName,
					toUserName, null);
			if (MessageType.Text.toString().equalsIgnoreCase(msgType)) {
				responseStr = new TextSession(datas).execute();
			} else if (MessageType.Event.toString().equalsIgnoreCase(msgType)) {
				responseStr = new EventSession(datas).execute();
			} else if (MessageType.Image.toString().equalsIgnoreCase(msgType)) {
				responseStr = new ImageSession(datas).execute();
			} else if (MessageType.Voice.toString().equalsIgnoreCase(msgType)) {
				responseStr = new VoiceSession(datas).execute();
			} else if (MessageType.Location.toString()
					.equalsIgnoreCase(msgType)) {
				responseStr = new LocationSession(datas).execute();
			}
			return responseStr;
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "error";
	}

	public String bytes2Hex(byte[] bts) {
		String des = "";
		String tmp = null;
		for (int i = 0; i < bts.length; i++) {
			tmp = (Integer.toHexString(bts[i] & 0xFF));
			if (tmp.length() == 1) {
				des += "0";
			}
			des += tmp;
		}
		return des;
	}
}
