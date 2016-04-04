package com.cg.springmvc.delegate;

import com.cg.springmvc.model.Otp;
import com.cg.springmvc.model.User;
import com.cg.springmvc.service.OtpService;

public class OtpDelegate {

	private OtpService otpService;

	public OtpService getOtpService() {
		return this.otpService;
	}

	public void setOtpService(OtpService otpService) {
		this.otpService = otpService;
	}
	
	public Otp addOrUpdateOtp(String otpGenerated, User user){
		return otpService.addOrUpdateOtp(otpGenerated, user);
	}

	public void removeOtpById(Integer id) {
		otpService.removeOtp(id);
	}

	public Otp getOtpById(Integer id) {
		return otpService.getOtpById(id);
	}

}
