package com.cg.springmvc.service.impl;

import com.cg.springmvc.dao.OtpDAO;
import com.cg.springmvc.model.Otp;
import com.cg.springmvc.service.OtpService;

public class OtpServiceImpl implements OtpService {

	private OtpDAO otpDao;

	public OtpDAO getOtpDao() {
		return otpDao;
	}

	public void setOtpDao(OtpDAO otpDao) {
		this.otpDao = otpDao;
	}

	public void addOtp(Otp otp) {
		otpDao.addOtp(otp);
	}

	public void removeOtp(Integer id) {
		otpDao.removeOtp(id);
	}

	public Otp getOtpById(Integer id) {
		return otpDao.getOtpById(id);
	}

	public Otp updateOtp(Otp otp) {
		return otpDao.updateOtp(otp);
	}

}
