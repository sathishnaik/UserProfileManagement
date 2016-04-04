package com.cg.springmvc.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.cg.springmvc.delegate.LoginDelegate;
import com.cg.springmvc.model.User;

public class OtpValidator implements Validator {

	@Autowired
	private LoginDelegate loginDelegate;

	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validate(Object obj, Errors errors) {
		User user = (User) obj;
		String username = user.getUsername();
		validateUser(username, errors);
	}

	private void validateUser(String username, Errors errors) {
		boolean isUserExists = loginDelegate.isUserIDExists(username);

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username","required.username", "Username is required");

		// Check if username does not exists
		if (!isUserExists && !errors.hasErrors())
			errors.rejectValue("username", "OtpValidator.username.not.exists");

	}
}
