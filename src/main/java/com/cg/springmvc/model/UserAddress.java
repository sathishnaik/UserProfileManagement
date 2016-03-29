package com.cg.springmvc.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "ADDRESS")
public class UserAddress implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6421405151616365417L;

	@Id
	   @GeneratedValue(strategy=GenerationType.IDENTITY,generator="foreign")
	   @GenericGenerator(name="foreign", strategy = "foreign", 
	   parameters={@Parameter(name="property", value="userObj")}) 
	@Column (name="USER_ID")
	private int addressId;
	
	@Column(name = "HOUSE_NO")
	private Integer houseNo;
	
	@Column(name="STREET")
	private String street;
	
	@Column(name="CITY")
	private String city;
	
	@Column(name="COUNTRY")
	private String country;	
	
	@OneToOne(optional=false)
	@PrimaryKeyJoinColumn (name="USER_ID", referencedColumnName="LOGIN_ID")
	private User userObj;
	
	@Column(name="STATE_FK")
	private Integer state_id;
	
	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public Integer getHouseNo() {
		return houseNo;
	}

	public void setHouseNo(Integer houseNo) {
		this.houseNo = houseNo;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	
	public Integer getState_id() {
		return state_id;
	}

	public void setState_id(Integer state_id) {
		this.state_id = state_id;
	}

	public User getUserObj() {
		return userObj;
	}

	public void setUserObj(User userObj) {
		this.userObj = userObj;
	}

}
