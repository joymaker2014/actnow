/**
 * 
 */
package com.jm.model;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.jm.constants.EventStatus;

/**
 * @author LuZheqi
 * 
 */
@Entity
@Table(name = "t_originalevent")
public class OriginalEvent {
	@Id
	private String id;

	@OneToOne
	@JoinColumn(name = "openid")
	private User user;

	private int category;

	private int type;

	private int district;

	private int businessCircle;

	private String address;

	private double longitude;

	private double latitude;

	@Temporal(TemporalType.TIMESTAMP)
	private Date time;

	private ArrayList<String> details;

	@Transient
	private EventStatus status;

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
	 * @return the category
	 */
	public int getCategory() {
		return category;
	}

	/**
	 * @param category
	 *            the category to set
	 */
	public void setCategory(int category) {
		this.category = category;
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
	 * @return the district
	 */
	public int getDistrict() {
		return district;
	}

	/**
	 * @param district
	 *            the district to set
	 */
	public void setDistrict(int district) {
		this.district = district;
	}

	/**
	 * @return the businessCircle
	 */
	public int getBusinessCircle() {
		return businessCircle;
	}

	/**
	 * @param businessCircle
	 *            the businessCircle to set
	 */
	public void setBusinessCircle(int businessCircle) {
		this.businessCircle = businessCircle;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the longitude
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * @param longitude
	 *            the longitude to set
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	/**
	 * @return the latitude
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude
	 *            the latitude to set
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	/**
	 * @return the time
	 */
	public Date getTime() {
		return time;
	}

	/**
	 * @param time
	 *            the time to set
	 */
	public void setTime(Date time) {
		this.time = time;
	}

	/**
	 * @return the details
	 */
	public ArrayList<String> getDetails() {
		return details;
	}

	/**
	 * @param details
	 *            the details to set
	 */
	public void setDetails(ArrayList<String> details) {
		this.details = details;
	}

	/**
	 * @return the status
	 */
	public EventStatus getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(EventStatus status) {
		this.status = status;
	}
}
