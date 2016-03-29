package com.cg.springmvc.dao;

import com.cg.springmvc.model.Otp;

public interface OtpDAO {

	public void addOtp(Otp otp);

	public void removeOtp(Integer id);

	public Otp getOtpById(Integer id);

	public Otp updateOtp(Otp otp);

}
