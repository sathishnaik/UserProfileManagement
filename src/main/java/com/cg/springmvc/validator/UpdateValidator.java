package com.cg.springmvc.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.cg.springmvc.model.User;

public class UpdateValidator implements Validator {

	private static final String emailregex = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
	private static final String ID_PATTERN = "[0-9]+";
	private static final String PASSWORD_PATTERN = "^[0-9a-z]+$";

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		User formUserObj = (User) target;
		validateUpdate(formUserObj, errors);

	}

	private void validateUpdate(User formUserObj, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username","required.username", "Username is required field");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password","required.password", "Password is required field");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email","required.email", "Email is required field");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userAddress.houseNo", "required.userAddress.houseNo", "House Number is required field");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userAddress.street","required.userAddress.street", "Street is required field");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userAddress.city","required.userAddress.password", "City is required field");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userAddress.state_id","required.userAddress.state_id", "State is required field");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userAddress.country", "required.userAddress.country", "Country is required field");

		if (!errors.hasErrors()) {

			if (!formUserObj.getUserAddress().getHouseNo().toString().matches(ID_PATTERN))
				errors.rejectValue("userAddress.houseNo", "RegisterValidator.userAddress.houseNo");

			if (!formUserObj.getEmail().matches(emailregex)) {
				errors.rejectValue("email", "RegisterValidator.email");
			}
			
			if (!formUserObj.getPassword().matches(PASSWORD_PATTERN))
				errors.rejectValue("password", "RegisterValidator.password");

		}
	}

}
