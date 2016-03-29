package com.cg.springmvc.controller;

import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;

import com.cg.springmvc.delegate.OtpDelegate;
import com.cg.springmvc.delegate.UserDelegate;
import com.cg.springmvc.model.User;
import com.cg.springmvc.model.UserAddress;
import com.cg.springmvc.utils.ConstantUtil;
import com.cg.springmvc.utils.UserProfileUtil;
import com.cg.springmvc.validator.RegisterValidator;
import com.cg.springmvc.validator.UserValidator;

@Controller
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private OtpDelegate otpDelegate;

	@Autowired
	private UserDelegate userDelegate;

	@Autowired
	UserValidator validator;

	@Autowired
	RegisterValidator registerValidator;
	
	/**
	 * Home page 
	 * @param request
	 * @param response
	 * @return
	 */

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView displayHomePage(HttpServletRequest request, HttpServletResponse response) {
		logger.info("Index/Login page");
		ModelAndView model = new ModelAndView(ConstantUtil.VIEW_HOME);
		User loginBean = new User();
		model.addObject(ConstantUtil.MODEL_OBJ_LOGIN_BEAN, loginBean);
		return model;
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param loginBean
	 * @param result
	 * @param status
	 * @param map
	 * @return
	 */

	@RequestMapping(value = ConstantUtil.URL_LOGIN, params = ConstantUtil.PARAM_LOGIN, method = RequestMethod.POST)
	public ModelAndView executeLogin(HttpServletRequest request, HttpServletResponse response,
			@Valid @ModelAttribute(ConstantUtil.MODEL_OBJ_LOGIN_BEAN) User loginBean,
			BindingResult result, SessionStatus status, Map<String, Object> map) {
		logger.info("Inside Login user method");
		ModelAndView model = null;
		try {
			validator.validate(loginBean, result);
			User bean = userDelegate.getUserByUsername(loginBean.getUsername());
			if (result.hasErrors()) {
				FieldError errorField = result.getFieldError(ConstantUtil.FIELD_OTP_OTP_ID);
				//remove expired OTP
				if(errorField!=null && errorField.getField()!=null && errorField.getField().contains(ConstantUtil.FIELD_OTP_OTP_ID)){
					otpDelegate.removeOtpById(bean.getOtp().getOtpId());
				}
				
				model = new ModelAndView(ConstantUtil.VIEW_HOME);
				model.addObject(ConstantUtil.MODEL_OBJ_LOGIN_BEAN, loginBean);
				return model;
			} else {
				logger.info("User Login Successful");
				//Encode Base64 for image
				byte[] encodeBase64 = Base64.encodeBase64(bean.getImage());
				String base64Encoded = new String(encodeBase64, ConstantUtil.UTF_8);

				model = new ModelAndView(ConstantUtil.VIEW_UPDATE);
				model.addObject(ConstantUtil.MODEL_OBJ_UPDATE_BEAN, bean);
				model.addObject(ConstantUtil.USER_IMAGE, base64Encoded);
				map.put(ConstantUtil.STATES, UserProfileUtil.getStates(userDelegate.getStates()));
				//Remove otp on successful login
				otpDelegate.removeOtpById(bean.getOtp().getOtpId());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
	/**
	 * Go to Register Page
	 * @param request
	 * @param response
	 * @param loginBean
	 * @param result
	 * @param status
	 * @param map
	 * @return
	 */

	@RequestMapping(value = ConstantUtil.URL_LOGIN, params = ConstantUtil.PARAM_REGISTER, method = RequestMethod.POST)
	public String goToRegister(HttpServletRequest request,
			HttpServletResponse response,
			@Valid @ModelAttribute(ConstantUtil.MODEL_OBJ_LOGIN_BEAN) User loginBean,
			BindingResult result, SessionStatus status, Map<String, Object> map) {
		logger.info("Go to Register User page");
		map.put(ConstantUtil.STATES, UserProfileUtil.getStates(userDelegate.getStates()));
		return ConstantUtil.VIEW_REGISTER;
	}

	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws ServletException {
		// Convert multipart object to byte[]
		binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());

	}
	
	/**
	 * User registration page, User can register themselves
	 * @param request
	 * @param response
	 * @param file
	 * @param loginBean
	 * @param map
	 * @param result
	 * @param status
	 * @return
	 */

	@RequestMapping(value = ConstantUtil.URL_REGISTER, method = RequestMethod.POST)
	public ModelAndView register(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(ConstantUtil.REQUEST_PARAM_IMAGE) MultipartFile file,
			@Valid @ModelAttribute(ConstantUtil.MODEL_OBJ_LOGIN_BEAN) User loginBean,
			Map<String, Object> map, BindingResult result, SessionStatus status) {
		logger.info("Register User page");
		ModelAndView model = null;
		try {
			registerValidator.validate(loginBean, result);
			map.put(ConstantUtil.STATES, UserProfileUtil.getStates(userDelegate.getStates()));

			if (result.hasErrors()) {
				model = new ModelAndView(ConstantUtil.VIEW_REGISTER);
				model.addObject(ConstantUtil.MODEL_OBJ_LOGIN_BEAN, loginBean);
				return model;
			} else {
				loginBean.setImage(file.getBytes());
				UserAddress userAddress = loginBean.getUserAddress();
				userAddress.setUserObj(loginBean);
				loginBean.setUserAddress(userAddress);
				userDelegate.addUser(loginBean);
				model = new ModelAndView(ConstantUtil.VIEW_HOME);
				model.addObject(ConstantUtil.MODEL_OBJ_LOGIN_BEAN, loginBean);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

}
