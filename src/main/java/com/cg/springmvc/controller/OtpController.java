package com.cg.springmvc.controller;

import java.sql.Timestamp;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.cg.springmvc.OTPGenerator;
import com.cg.springmvc.delegate.OtpDelegate;
import com.cg.springmvc.delegate.UserDelegate;
import com.cg.springmvc.model.Otp;
import com.cg.springmvc.model.User;
import com.cg.springmvc.utils.ConstantUtil;
import com.cg.springmvc.validator.OtpValidator;

@Controller
public class OtpController {
	
	private static final Logger logger = LoggerFactory.getLogger(OtpController.class);

	@Autowired
	private OtpDelegate otpDelegate;

	@Autowired
	private UserDelegate userDelegate;

	@Autowired
	OtpValidator validator;
	
	/**
	 * Go  to generate OTP page, an 8 character OTP will be generated
	 * @param request
	 * @param loginBean
	 * @param result
	 * @param status
	 * @return
	 */

	@RequestMapping(value = ConstantUtil.GENERATE_OTP, method = RequestMethod.POST)
	public ModelAndView generateOTP(HttpServletRequest request, @Valid @ModelAttribute(ConstantUtil.MODEL_OBJ_LOGIN_BEAN) User loginBean,
			BindingResult result, SessionStatus status) {
		ModelAndView model = null;
		try {
			logger.info("inside generate OTP");
			User user = userDelegate.getUserByUsername(loginBean.getUsername());
			validator.validate(loginBean, result);
			if (result.hasErrors()) {
				model = new ModelAndView(ConstantUtil.VIEW_HOME);
				model.addObject(ConstantUtil.MODEL_OBJ_LOGIN_BEAN, loginBean);
				return model;
			} else {
				String otpGenerated = OTPGenerator.generateOTP();
				model = new ModelAndView(ConstantUtil.VIEW_OTP);
				
				Otp otpObj = user.getOtp();
				otpObj = fillOtp(otpGenerated, user.getOtp(), user);

				// Update existing OTP
				if (otpObj.getOtpId() != 0) 
					otpObj = otpDelegate.updateOtp(otpObj);
				// User creating OTP first time, Add new OTP
				 else 
					 otpDelegate.addOtp(otpObj);
				model.addObject(ConstantUtil.MODEL_OBJ_OTP_BEAN, user);
				return model;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	

	@RequestMapping(value = ConstantUtil.URL_BACK_TO_LOGIN, method = RequestMethod.GET)
	public String backToLogin(HttpServletRequest request,
			HttpServletResponse response,
			@ModelAttribute(ConstantUtil.MODEL_OBJ_LOGIN_BEAN) User loginBean) {
		logger.info("Back to Login Page");
		return ConstantUtil.VIEW_HOME;
	}
	
	private Otp fillOtp(String generatedOtpCode, Otp otpObj, User user){
		
		if (otpObj != null) {
			otpObj.setOtpCode(generatedOtpCode);
			otpObj.setOtpTime(new Timestamp(Calendar.getInstance().getTimeInMillis()));
			return otpObj;
		} else {
			// User creating OTP first time, Add new OTP
			Otp newOtp = new Otp();
			newOtp.setOtpCode(generatedOtpCode);
			newOtp.setOtpTime(new Timestamp(Calendar.getInstance().getTimeInMillis()));
			user.setOtp(newOtp);
			newOtp.setUser(user);
			return newOtp;
		}
		
	}

}
