/**
 * 
 */
package com.jm.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author LuZheqi
 * 
 */
@Entity
@Table(name = "t_user")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private String openid;
	private boolean subscribe;
	private String nickname;
	private int sex;
	private String city;
	private String country;
	private String province;
	private String language;
	private String headImageUrl;

	@Temporal(TemporalType.TIMESTAMP)
	private Date subscribeTime;
	private long account;
	private long credit;
	private int blackNum;

	public User() {

	}

	/**
	 * @return the openid
	 */
	public String getOpenid() {
		return openid;
	}

	/**
	 * @param openid
	 *            the openid to set
	 */
	public void setOpenid(String openid) {
		this.openid = openid;
	}

	/**
	 * @return the subscribe
	 */
	public boolean isSubscribe() {
		return subscribe;
	}

	/**
	 * @param subscribe
	 *            the subscribe to set
	 */
	public void setSubscribe(boolean subscribe) {
		this.subscribe = subscribe;
	}

	/**
	 * @return the nickname
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * @param nickname
	 *            the nickname to set
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * @return the sex
	 */
	public int getSex() {
		return sex;
	}

	/**
	 * @param sex
	 *            the sex to set
	 */
	public void setSex(int sex) {
		this.sex = sex;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country
	 *            the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the province
	 */
	public String getProvince() {
		return province;
	}

	/**
	 * @param province
	 *            the province to set
	 */
	public void setProvince(String province) {
		this.province = province;
	}

	/**
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * @param language
	 *            the language to set
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * @return the headImageUrl
	 */
	public String getHeadImageUrl() {
		return headImageUrl;
	}

	/**
	 * @param headImageUrl
	 *            the headImageUrl to set
	 */
	public void setHeadImageUrl(String headImageUrl) {
		this.headImageUrl = headImageUrl;
	}

	/**
	 * @return the subscribeTime
	 */
	public Date getSubscribeTime() {
		return subscribeTime;
	}

	/**
	 * @param subscribeTime
	 *            the subscribeTime to set
	 */
	public void setSubscribeTime(Date subscribeTime) {
		this.subscribeTime = subscribeTime;
	}

	/**
	 * @return the account
	 */
	public long getAccount() {
		return account;
	}

	/**
	 * @param account
	 *            the account to set
	 */
	public void setAccount(long account) {
		this.account = account;
	}

	/**
	 * @return the credit
	 */
	public long getCredit() {
		return credit;
	}

	/**
	 * @param credit
	 *            the credit to set
	 */
	public void setCredit(long credit) {
		this.credit = credit;
	}

	/**
	 * @return the blackNum
	 */
	public int getBlackNum() {
		return blackNum;
	}

	/**
	 * @param blackNum
	 *            the blackNum to set
	 */
	public void setBlackNum(int blackNum) {
		this.blackNum = blackNum;
	}

}
