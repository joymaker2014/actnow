/**
 * 
 */
package com.jm.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author LuZheqi
 * 
 */
@Entity
@Table(name = "t_goods")
public class Goods {
	@Id
	private String id;

	private int type;

	private int cardNum;

	private int cardPwd;

	private int value;

	private boolean used;

	private String expiredDate;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * @return the cardNum
	 */
	public int getCardNum() {
		return cardNum;
	}

	/**
	 * @param cardNum
	 *            the cardNum to set
	 */
	public void setCardNum(int cardNum) {
		this.cardNum = cardNum;
	}

	/**
	 * @return the cardPwd
	 */
	public int getCardPwd() {
		return cardPwd;
	}

	/**
	 * @param cardPwd
	 *            the cardPwd to set
	 */
	public void setCardPwd(int cardPwd) {
		this.cardPwd = cardPwd;
	}

	/**
	 * @return the value
	 */
	public int getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(int value) {
		this.value = value;
	}

	/**
	 * @return the used
	 */
	public boolean isUsed() {
		return used;
	}

	/**
	 * @param used
	 *            the used to set
	 */
	public void setUsed(boolean used) {
		this.used = used;
	}

	/**
	 * @return the expiredDate
	 */
	public String getExpiredDate() {
		return expiredDate;
	}

	/**
	 * @param expiredDate
	 *            the expiredDate to set
	 */
	public void setExpiredDate(String expiredDate) {
		this.expiredDate = expiredDate;
	}
}
