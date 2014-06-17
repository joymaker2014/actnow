/**
 * 
 */
package com.jm.controller;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jm.constants.UrlConstants;
import com.jm.https.DefaultSSLSocketFactory;

/**
 * @author LuZheqi
 * 
 */
public class WeixinController {

	public static void main(String[] args) {
		try {
			HttpClient httpClient = DefaultSSLSocketFactory.getInstance()
					.wrapedHttpClient();
			HttpGet get = new HttpGet(
					"https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
							+ UrlConstants.APP_ID + "&secret="
							+ UrlConstants.APP_SECRET);
			HttpResponse response = httpClient.execute(get);

			String jsonStr = EntityUtils
					.toString(response.getEntity(), "utf-8");
			JSONObject object = JSON.parseObject(jsonStr);
			System.out.println(object.getString("access_token"));
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
