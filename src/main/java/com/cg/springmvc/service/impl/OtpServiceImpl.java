package com.cg.springmvc.service.impl;

import java.sql.Timestamp;
import java.util.Calendar;

import com.cg.springmvc.dao.OtpDAO;
import com.cg.springmvc.model.Otp;
import com.cg.springmvc.model.User;
import com.cg.springmvc.service.OtpService;

public class OtpServiceImpl implements OtpService {

	private OtpDAO otpDao;

	public OtpDAO getOtpDao() {
		return otpDao;
	}

	public void setOtpDao(OtpDAO otpDao) {
		this.otpDao = otpDao;
	}
	
	public Otp addOrUpdateOtp(String otpGenerated, User user){
		if(user.getOtp()!=null){
			user.getOtp().setOtpCode(otpGenerated);
			user.getOtp().setOtpTime(new Timestamp(Calendar.getInstance().getTimeInMillis()));
			return  otpDao.updateOtp(user.getOtp());
		}else{
			Otp newOtp = new Otp();
			newOtp.setOtpCode(otpGenerated);
			newOtp.setOtpTime(new Timestamp(Calendar.getInstance().getTimeInMillis()));
			user.setOtp(newOtp);
			newOtp.setUser(user);
			otpDao.addOtp(newOtp);
			return newOtp;
		}
	}

	public void removeOtp(Integer id) {
		otpDao.removeOtp(id);
	}

	public Otp getOtpById(Integer id) {
		return otpDao.getOtpById(id);
	}

}
