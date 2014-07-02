/**
 * 
 */
package com.jm.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author mzl
 * 
 */
@Entity
@Table(name = "t_exchangerecord")
public class ExchangeRecord {
	@Id
	private String id;

	private String openid;
	
	private int type;

	private int cardNum;

	private int cardPwd;

	private int value;

	private String ExchangeDate;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getCardNum() {
		return cardNum;
	}

	public void setCardNum(int cardNum) {
		this.cardNum = cardNum;
	}

	public int getCardPwd() {
		return cardPwd;
	}

	public void setCardPwd(int cardPwd) {
		this.cardPwd = cardPwd;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getExchangeDate() {
		return ExchangeDate;
	}

	public void setExchangeDate(String exchangeDate) {
		ExchangeDate = exchangeDate;
	}
}
