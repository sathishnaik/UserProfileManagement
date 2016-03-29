package com.cg.springmvc.service;

import com.cg.springmvc.model.Otp;

public interface OtpService {
	
	public void addOtp(Otp otp);
	public void removeOtp(Integer id);
	 public Otp getOtpById(Integer id);
	 public Otp updateOtp(Otp otp);

}
