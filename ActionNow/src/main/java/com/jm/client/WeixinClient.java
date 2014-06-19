/**
 * 
 */
package com.jm.client;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jm.client.https.DefaultSSLSocketFactory;
import com.jm.constants.UrlConstants;
import com.jm.model.User;
import com.jm.model.menu.Button;
import com.jm.model.menu.CommonButton;
import com.jm.model.menu.ComplexButton;
import com.jm.model.menu.Menu;
import com.jm.timer.accesstoken.AccessTokenTimer;
import com.jm.util.ServiceUtils;

/**
 * @author LuZheqi
 * 
 */
public class WeixinClient {
	public static User getUserInfo(String openid) {
		User user = new User();
		try {
			String url = UrlConstants.GET_USER_INFO_URL.replace("ACCESS_TOKEN",
					AccessTokenTimer.getAccessToken())
					.replace("OPENID", openid);

			HttpClient httpClient = DefaultSSLSocketFactory.getInstance()
					.wrapedHttpClient();
			HttpGet get = new HttpGet(url);
			System.out.println("Getting User Info!");
			HttpResponse response = httpClient.execute(get);

			String jsonStr = EntityUtils
					.toString(response.getEntity(), "UTF-8");
			JSONObject object = JSON.parseObject(jsonStr);

			System.out.println("Got User Info!");

			user.setAccount(0);
			user.setBlackNum(0);
			user.setCredit(0);
			user.setOpenid(openid);
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
			} else {
				List<User> users = ServiceUtils.getUserservice()
						.findUserOrderByNicknameLikeUserDesc();
				user.setNickname("user1");
				user.setCity("北京市");
				user.setCountry("朝阳区");
				user.setHeadImageUrl(null);
				user.setProvince("北京市");
				user.setSex(1);
				user.setSubscribe(true);
				user.setSubscribeTime(new Date());
				if (null == users || users.isEmpty()) {
					user.setNickname("user1");
				} else {
					String maxUserName = users.get(0).getNickname();
					String maxSuffix = maxUserName.substring(4) + 1;
					user.setNickname("user" + maxSuffix);
				}
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return user;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WeixinClient client = new WeixinClient();
		client.setButton();
	}

	public void setButton() {
		CommonButton btn11 = new CommonButton();
		btn11.setName("开始分享");
		btn11.setType("click");
		btn11.setKey("11");

		CommonButton btn12 = new CommonButton();
		btn12.setName("结束分享");
		btn12.setType("click");
		btn12.setKey("12");

		CommonButton btn13 = new CommonButton();
		btn13.setName("查询分享");
		btn13.setType("click");
		btn13.setKey("13");

		CommonButton btn21 = new CommonButton();
		btn21.setName("奖品信息");
		btn21.setType("click");
		btn21.setKey("21");

		CommonButton btn22 = new CommonButton();
		btn22.setName("兑换奖品");
		btn22.setType("click");
		btn22.setKey("22");

		ComplexButton mainBtn1 = new ComplexButton();
		mainBtn1.setName("身边的事");
		mainBtn1.setSub_button(new CommonButton[] { btn11, btn12, btn13, });

		ComplexButton mainBtn2 = new ComplexButton();
		mainBtn2.setName("奖品兑换");
		mainBtn2.setSub_button(new CommonButton[] { btn21, btn22 });

		Menu menu = new Menu();
		menu.setButton(new Button[] { mainBtn1, mainBtn2 });

		try {
			String url = UrlConstants.CREATE_MENU_URL.replace("ACCESS_TOKEN",
					AccessTokenTimer.getAccessToken());
			// 将菜单对象转换成json字符串
			String jsonMenu = JSONObject.toJSONString(menu);

			HttpClient httpClient = DefaultSSLSocketFactory.getInstance()
					.wrapedHttpClient();
			HttpPost post = new HttpPost(url);
			post.setEntity(new StringEntity(jsonMenu, "UTF-8"));
			HttpResponse response = httpClient.execute(post);

			String jsonStr = EntityUtils
					.toString(response.getEntity(), "UTF-8");
			JSONObject object = JSON.parseObject(jsonStr);
			System.out.println(object.getString("errmsg"));
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
