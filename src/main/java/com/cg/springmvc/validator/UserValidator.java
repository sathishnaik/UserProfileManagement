package com.cg.springmvc.validator;

import java.sql.Timestamp;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.cg.springmvc.delegate.LoginDelegate;
import com.cg.springmvc.delegate.OtpDelegate;
import com.cg.springmvc.delegate.UserDelegate;
import com.cg.springmvc.model.User;

public class UserValidator implements Validator{
	
	@Autowired
	private LoginDelegate loginDelegate;

	@Autowired
	private UserDelegate userDelegate;

	@Autowired
	private OtpDelegate otpDelegate;

	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	  public void validate(Object obj, Errors errors) {
	    User formUserObj = (User) obj;
	    validateLogin(formUserObj, errors);
	  }
	
	private void validateLogin(User formUserObj, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "required.username", "Username is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "required.password", "Password is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "otp.otpCode", "required.otp.otpCode", "Otp is required");
		
		User userFromDB = userDelegate.getUserByUsername(formUserObj.getUsername());
		boolean isValidUser = loginDelegate.isValidUser(formUserObj.getUsername().trim(), formUserObj.getPassword().trim());
	    
	    if(!errors.hasErrors()){
	    	//check if username entered exists in database 
	    	if(userFromDB==null){
	    		errors.rejectValue("username", "OtpValidator.username.not.exists");
	    	}
	    	//check if password entered is correct
	    	if(!isValidUser){
	    		errors.rejectValue("password", "UserValidator.password.wrong");
	    	}
	    	if(userFromDB!=null && userFromDB.getOtp()!=null){

	    		Timestamp otpTime = userFromDB.getOtp().getOtpTime();
	    		Timestamp currentTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
	    		
	    		long timeElapsed = (currentTime.getTime()-otpTime.getTime());
	    		if(timeElapsed>100000){
	    			errors.rejectValue("otp.otpId", "UserValidator.otp.otpId.expired");
	    		}
	    		
	    		// check whether user have entered correct OTP
	    		if(!userFromDB.getOtp().getOtpCode().equals(formUserObj.getOtp().getOtpCode())){
					errors.rejectValue("otp.otpCode", "UserValidator.otp.otpCode.wrong");
				}
	    	}else {
	    		// check whether user have generated OTP
				errors.rejectValue("otp.otpCode", "UserValidator.otp.otpCode.generate");
			}
	    }
   
      }
}
