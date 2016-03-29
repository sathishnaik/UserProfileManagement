package com.cg.springmvc.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "USER")
public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4803606127981541460L;

	@Id
	@Column(name = "LOGIN_ID")
	@GeneratedValue
	private int id;
	
	@Column(name = "USER_NAME")
	private String username;
	
	@Column(name="PASSWORD")
	private String password;
	
	@Column(name="EMAIL_ID")
	private String email;
	
	@Column(name="IMAGE", columnDefinition="blob")
	@Lob
	private byte[] image;
	
	@OneToOne(mappedBy="user", fetch=FetchType.EAGER)
	private Otp otp;
	
	@OneToOne(mappedBy="userObj", cascade={CascadeType.ALL,}, fetch=FetchType.EAGER)
	@PrimaryKeyJoinColumn (name="LOGIN_ID", referencedColumnName="USER_ID")
	private UserAddress userAddress;
	
	public Otp getOtp() {
		return otp;
	}

	public void setOtp(Otp otp) {
		this.otp = otp;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public byte[] getImage() {
		return this.image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

/*	public String getOtpCode() {
		return otpCode;
	}

	public void setOtpCode(String otpCode) {
		this.otpCode = otpCode;
	}*/

	public UserAddress getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(UserAddress userAddress) {
		this.userAddress = userAddress;
		this.id = userAddress.getAddressId();
	}
	
}
