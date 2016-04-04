package com.cg.springmvc.controller;

import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;

import com.cg.springmvc.delegate.OtpDelegate;
import com.cg.springmvc.delegate.UserDelegate;
import com.cg.springmvc.model.User;
import com.cg.springmvc.utils.ConstantUtil;
import com.cg.springmvc.utils.SessionUtil;
import com.cg.springmvc.utils.UserProfileUtil;
import com.cg.springmvc.validator.LoginValidator;
import com.cg.springmvc.validator.RegisterValidator;

@Controller
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private OtpDelegate otpDelegate;

	@Autowired
	private UserDelegate userDelegate;

	@Autowired
	LoginValidator validator;

	@Autowired
	RegisterValidator registerValidator;
	
	/**
	 * 
	 * Handles requests for the application home page.
	 * Home page 
	 * @param request
	 * @param response
	 * @return
	 */

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView displayHomePage(HttpServletRequest request) {
		logger.info("Index/Login page");
		ModelAndView model = new ModelAndView(ConstantUtil.VIEW_HOME);
		model.addObject(ConstantUtil.MODEL_OBJ_LOGIN_BEAN, new User());
		SessionUtil.createNewSession(request);
		SessionUtil.putSessionValue(request, ConstantUtil.STATES, UserProfileUtil.getStates(userDelegate.getStates()));
		return model;
	}
	
	/**
	 * 
	 * @param request
	 * @param loginBean
	 * @param result
	 * @return
	 */

	@RequestMapping(value = ConstantUtil.URL_LOGIN, params = ConstantUtil.PARAM_LOGIN, method = RequestMethod.POST)
	public ModelAndView executeLogin(HttpServletRequest request, @Valid @ModelAttribute(ConstantUtil.MODEL_OBJ_LOGIN_BEAN) User loginBean,
			BindingResult result) {
		logger.info("Inside Login user method");
		ModelAndView model = null;
		try {
			validator.validate(loginBean, result);
			User bean = userDelegate.getUserByUsername(loginBean.getUsername());
			//Validation error messages
			if (result.hasErrors()) {
				model = new ModelAndView(ConstantUtil.VIEW_HOME);
				model.addObject(ConstantUtil.MODEL_OBJ_LOGIN_BEAN, loginBean);
				return model;
			} else {
				logger.info("User Login Successful");
				SessionUtil.createNewSession(request);
				SessionUtil.putSessionValue(request, ConstantUtil.LOGGED_IN_USER, bean);
				SessionUtil.putSessionValue(request, ConstantUtil.STATES, UserProfileUtil.getStates(userDelegate.getStates()));
				//Encode Base64 for image
				byte[] encodeImage = Base64.encodeBase64(bean.getImage());
				String encodedImage = new String(encodeImage, ConstantUtil.UTF_8);

				model = new ModelAndView(ConstantUtil.VIEW_UPDATE);
				model.addObject(ConstantUtil.MODEL_OBJ_UPDATE_BEAN, bean);
				model.addObject(ConstantUtil.USER_IMAGE, encodedImage);
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
	 * @param loginBean
	 * @param result
	 * @param map
	 * @return
	 */

	@RequestMapping(value = ConstantUtil.URL_LOGIN, params = ConstantUtil.PARAM_REGISTER, method = RequestMethod.POST)
	public String goToRegister(@Valid @ModelAttribute(ConstantUtil.MODEL_OBJ_LOGIN_BEAN) User loginBean,
			 Map<String, Object> map) {
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
	 * @param file
	 * @param loginBean
	 * @param result
	 * @return
	 */

	@RequestMapping(value = ConstantUtil.URL_REGISTER, method = RequestMethod.POST)
	public ModelAndView register(@RequestParam(ConstantUtil.REQUEST_PARAM_IMAGE) MultipartFile file,
			 @ModelAttribute(ConstantUtil.MODEL_OBJ_LOGIN_BEAN)@Valid User loginBean,BindingResult result) {
		logger.info("Register User page");
		ModelAndView model =  new ModelAndView(ConstantUtil.VIEW_REGISTER);
		try {
			registerValidator.validate(loginBean, result);

			if (result.hasErrors()) {
				model.addObject(ConstantUtil.MODEL_OBJ_LOGIN_BEAN, loginBean);
				return model;
			} else {
				loginBean.setImage(file.getBytes());
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
