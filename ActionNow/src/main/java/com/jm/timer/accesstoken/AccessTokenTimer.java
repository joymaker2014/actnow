/**
 * 
 */
package com.jm.timer.accesstoken;

import java.net.UnknownHostException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jm.client.https.DefaultSSLSocketFactory;
import com.jm.constants.UrlConstants;
import com.jm.model.accesstoken.AccessToken;

/**
 * @author LuZheqi
 * 
 */
public class AccessTokenTimer extends TimerTask {
	private static final ReadWriteLock lock = new ReentrantReadWriteLock();
	private static final Lock readLock = lock.readLock();
	private static final Lock writeLock = lock.writeLock();
	private static AccessToken token = null;
	private static Timer timer = new Timer();

	public void init() {
		AccessTokenTimer timer = new AccessTokenTimer();
		AccessTokenTimer.timer.schedule(timer, 0,
				(AccessToken.getExpiresIn() - 200) * 1000L);
	}

	public void unit() {
		AccessTokenTimer.timer.cancel();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.TimerTask#run()
	 */
	@Override
	public void run() {
		setAccessToken();
	}

	public static String getAccessToken() {
		if (null == token) {
			setAccessToken();
		}
		readLock.lock();
		try {
			return token.getAccessToken();
		} finally {
			readLock.unlock();
		}
	}

	public static void setAccessToken() {
		writeLock.lock();
		try {
			while (!fetchAccessToken()) {
				try {
					Thread.sleep(60000L);
					System.out.println("********************Try Again!");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} finally {
			writeLock.unlock();
		}
	}

	public static boolean fetchAccessToken() {
		boolean setOK = true;
		try {
			HttpClient httpClient = DefaultSSLSocketFactory.getInstance()
					.wrapedHttpClient();
			HttpGet get = new HttpGet(UrlConstants.GET_TOKEN_URL.replace(
					"APPID", UrlConstants.APP_ID).replace("APPSECRET",
					UrlConstants.APP_SECRET));
			HttpResponse response = httpClient.execute(get);

			String jsonStr = EntityUtils
					.toString(response.getEntity(), "utf-8");
			JSONObject object = JSON.parseObject(jsonStr);
			System.out.println(object.getString("access_token"));
			if (null == token) {
				token = new AccessToken();
			}
			token.setAccessToken(object.getString("access_token"));
		} catch (UnknownHostException e) {
			e.printStackTrace();
			setOK = false;
		} catch (Exception e) {
			e.printStackTrace();
			setOK = false;
		}
		return setOK;
	}
}
