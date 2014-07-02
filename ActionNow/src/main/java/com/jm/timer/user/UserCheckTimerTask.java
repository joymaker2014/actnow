/**
 * 
 */
package com.jm.timer.user;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jm.client.WeixinClient;
import com.jm.client.https.DefaultSSLSocketFactory;
import com.jm.constants.UrlConstants;
import com.jm.model.User;
import com.jm.timer.OneTimeTrigger;
import com.jm.timer.TimerTask;
import com.jm.timer.accesstoken.AccessTokenTimer;
import com.jm.util.ServiceUtils;

/**
 * @author LuZheqi
 * 
 */
public class UserCheckTimerTask extends TimerTask {
	private final String openid;

	public UserCheckTimerTask(String openid) {
		super(new OneTimeTrigger(0));
		this.openid = openid;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jm.timer.TimerTask#run(long)
	 */
	@Override
	protected void run(long paramLong) {
		User user = ServiceUtils.getUserService().findUserById(openid);
		String regEx = "user.*";
		boolean result = Pattern.matches(regEx, user.getNickname());
		if (result) {
			try {
				String url = UrlConstants.GET_USER_INFO_URL.replace(
						"ACCESS_TOKEN", AccessTokenTimer.getAccessToken())
						.replace("OPENID", openid);

				HttpClient httpClient = DefaultSSLSocketFactory.getInstance()
						.wrapedHttpClient();
				HttpGet get = new HttpGet(url);
				System.out.println("Getting User Info!");
				HttpResponse response = httpClient.execute(get);

				String jsonStr = EntityUtils.toString(response.getEntity(),
						"UTF-8");
				JSONObject object = JSON.parseObject(jsonStr);

				if (null == object.get("errmsg")) {
					user.setSubscribe(object.getBooleanValue("subscribe"));
					user.setNickname(object.getString("nickname"));
					user.setSex(object.getIntValue("sex"));
					user.setCity(object.getString("city"));
					user.setProvince(object.getString("province"));
					user.setCountry(object.getString("country"));
					user.setLanguage(object.getString("language"));
					user.setHeadImageUrl(object.getString("headimgurl"));
					user.setSubscribeTime(object.getDate("subscribe_time"));
					ServiceUtils.getUserService().updateUser(user);
				}
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
}
