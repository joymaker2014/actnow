/**
 * 
 */
package com.jm.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author LuZheqi
 * 
 */
@Entity
@Table(name = "t_deal")
public class Deal {
	@Id
	private String id;

	private int account;

	private String timestamp;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "openid")
	private User user;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "goods_id")
	private Goods goods;

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
	 * @return the account
	 */
	public int getAccount() {
		return account;
	}

	/**
	 * @param account
	 *            the account to set
	 */
	public void setAccount(int account) {
		this.account = account;
	}

	/**
	 * @return the timestamp
	 */
	public String getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp
	 *            the timestamp to set
	 */
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

}
