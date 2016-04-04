package com.cg.springmvc.service;

import com.cg.springmvc.model.Otp;
import com.cg.springmvc.model.User;

public interface OtpService {

	public void removeOtp(Integer id);

	public Otp getOtpById(Integer id);

	public Otp addOrUpdateOtp(String otpGenerated, User user);

}
