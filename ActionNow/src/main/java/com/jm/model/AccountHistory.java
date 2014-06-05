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
@Table(name = "t_accounthistory")
public class AccountHistory {

	@Id
	private String id;

	private long value;

	private long offset;

	private String timestamp;

	private int reason;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "openid")
	private User user;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "original_event_id")
	private OriginalEvent originalEvent;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "deal_id")
	private Deal deal;

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
	 * @return the value
	 */
	public long getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(long value) {
		this.value = value;
	}

	/**
	 * @return the offset
	 */
	public long getOffset() {
		return offset;
	}

	/**
	 * @param offset
	 *            the offset to set
	 */
	public void setOffset(long offset) {
		this.offset = offset;
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
	 * @return the reason
	 */
	public int getReason() {
		return reason;
	}

	/**
	 * @param reason
	 *            the reason to set
	 */
	public void setReason(int reason) {
		this.reason = reason;
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

	/**
	 * @return the originalEvent
	 */
	public OriginalEvent getOriginalEvent() {
		return originalEvent;
	}

	/**
	 * @param originalEvent the originalEvent to set
	 */
	public void setOriginalEvent(OriginalEvent originalEvent) {
		this.originalEvent = originalEvent;
	}

	/**
	 * @return the deal
	 */
	public Deal getDeal() {
		return deal;
	}

	/**
	 * @param deal the deal to set
	 */
	public void setDeal(Deal deal) {
		this.deal = deal;
	}
}
