/**
 * 
 */
package com.jm.model;

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
@Table(name = "t_blacklist")
public class BlackList {
	@Id
	private String id;

	@OneToOne
	@JoinColumn(name = "openid")
	private User user;

	private String time;

	private boolean isPermanent;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * @return the isPermanent
	 */
	public boolean isPermanent() {
		return isPermanent;
	}

	/**
	 * @param isPermanent the isPermanent to set
	 */
	public void setPermanent(boolean isPermanent) {
		this.isPermanent = isPermanent;
	}
}
