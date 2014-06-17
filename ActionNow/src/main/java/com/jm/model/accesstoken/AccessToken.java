/**
 * 
 */
package com.jm.model.accesstoken;

/**
 * @author LuZheqi
 * 
 */
public class AccessToken {
	private static final int EXPIRE = 7200;
	private String accessToken;

	/**
	 * @return the accessToken
	 */
	public String getAccessToken() {
		return accessToken;
	}

	/**
	 * @param accessToken
	 *            the accessToken to set
	 */
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public static int getExpiresIn() {
		return EXPIRE;
	}

}
