package com.cg.springmvc.model;


import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "USER_OTP")
public class Otp implements Serializable{
	
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		@Id
		@Column(name = "OTP_ID")
		@GeneratedValue
		private int otpId;

		@OneToOne(fetch=FetchType.EAGER)
		@JoinColumn(name="USER_ID")
		private User user;
		
		@Column(name="OTP")
		private String otpCode;
		
		@Column(name="OTP_TIME")
		private Timestamp otpTime;

		public int getOtpId() {
			return otpId;
		}

		public void setOtpId(int otpId) {
			this.otpId = otpId;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public String getOtpCode() {
			return otpCode;
		}

		public void setOtpCode(String otpCode) {
			this.otpCode = otpCode;
		}

		public Timestamp getOtpTime() {
			return otpTime;
		}

		public void setOtpTime(Timestamp otpTime) {
			this.otpTime = otpTime;
		}

}
