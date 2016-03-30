package com.cg.springmvc.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.cg.springmvc.delegate.UserDelegate;
import com.cg.springmvc.model.User;
import com.cg.springmvc.utils.ConstantUtil;

public class RegisterValidator implements Validator{
	
	private static final Logger logger = LoggerFactory.getLogger(RegisterValidator.class);

	@Autowired
	private UserDelegate userDelegate;

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		User formUserObj = (User) target;
		validateRegister(formUserObj, errors);
	}
	
	
	private void validateRegister(User formUserObj, Errors errors) {
		logger.info("House No"+formUserObj.getUserAddress().getHouseNo());
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "required.username", "Username is required field");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "required.password", "Password is required field");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "required.email", "Email is required field");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userAddress.houseNo", "required.userAddress.houseNo", "House Number is required field");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userAddress.street", "required.userAddress.street", "Street is required field");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userAddress.city", "required.userAddress.password", "City is required field");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userAddress.state_id", "required.userAddress.state_id", "State is required field");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userAddress.country", "required.userAddress.country", "Country is required field");
		
		User userFromDB = userDelegate.getUserByUsername(formUserObj.getUsername());	
	    
	    if(!errors.hasErrors()){
	    	//check if username entered exists in database 
	    	if(userFromDB!=null &&userFromDB.getUsername().equals(formUserObj.getUsername())){
	    		errors.rejectValue("username", "RegisterValidator.username.exists");
	    	}
	    	//check for password range
	    	if(formUserObj.getPassword().length()<6||formUserObj.getPassword().length()>10){
	    		errors.rejectValue("password", "RegisterValidator.password.range");
	    	}
	    	//check if profile image selected for upload
	    	if(formUserObj.getImage().length==0){
	    		errors.rejectValue("image", "RegisterValidator.image");
	    	}
	    		   
	    	if (!formUserObj.getUserAddress().getHouseNo().toString().matches(ConstantUtil.ID_PATTERN))  
	    		errors.rejectValue("userAddress.houseNo", "RegisterValidator.userAddress.houseNo");  
	    	// check for password pattern for alphanumeric
	    	String password = formUserObj.getPassword();
	    	if (password.matches(ConstantUtil.PASSWORD_PATTERN)&& (!password.matches("[!0-9]*")&&!password.matches("[!A-Za-z]*"))) {
	    		logger.info("Password is alpha numeric");
	    	}else
		    	errors.rejectValue("password", "RegisterValidator.password");  
	    		
	    	if (!formUserObj.getEmail().matches(ConstantUtil.EMAIL_REGEX))   {
	    		errors.rejectValue("email", "RegisterValidator.email");  
	    	}
	    }
      }
}
