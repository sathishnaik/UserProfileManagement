package com.cg.springmvc.controller;

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

import com.cg.springmvc.delegate.UserDelegate;
import com.cg.springmvc.model.User;
import com.cg.springmvc.model.UserAddress;
import com.cg.springmvc.utils.ConstantUtil;
import com.cg.springmvc.utils.SessionUtil;
import com.cg.springmvc.utils.UserProfileUtil;
import com.cg.springmvc.validator.UpdateValidator;

@Controller
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserDelegate userDelegate;

	@Autowired
	UpdateValidator updateValidator;

	/**
	 * update user object
	 * @param request
	 * @param updateBean
	 * @param result
	 * @param file
	 * @return
	 */
	@RequestMapping(value = ConstantUtil.URL_UPDATE, params = ConstantUtil.PARAM_UPDATE, method = RequestMethod.POST)
	public ModelAndView userUpdate(HttpServletRequest request,
			@Valid @ModelAttribute(ConstantUtil.MODEL_OBJ_UPDATE_BEAN) User updateBean,
			BindingResult result, @RequestParam(ConstantUtil.PARAM_IMAGE) MultipartFile file) {
		logger.info("User Update method");
		ModelAndView model = new ModelAndView(ConstantUtil.VIEW_UPDATE);
		try {
			updateValidator.validate(updateBean, result);
			User userFromSession = (User)SessionUtil.getSessionValue(request, ConstantUtil.LOGGED_IN_USER);
			User user = userDelegate.getUserByUsername(updateBean.getUsername());
			if(user==null){
				user = userFromSession;
			}
			
			String filename = file.getOriginalFilename();
			String encodedImage = null;
			if (filename.isEmpty()) {
				byte[] encodeImage = Base64.encodeBase64(user.getImage());
				encodedImage = new String(encodeImage, ConstantUtil.UTF_8);
				updateBean.setImage(user.getImage());
			} else {
				byte[] encodeImage = Base64.encodeBase64(updateBean.getImage());
				encodedImage = new String(encodeImage, ConstantUtil.UTF_8);
			}
			
			model.addObject(ConstantUtil.USER_IMAGE, encodedImage);
			model.addObject(ConstantUtil.MODEL_OBJ_UPDATE_BEAN, updateBean);
			model.addObject(ConstantUtil.STATES, UserProfileUtil.getStates(userDelegate.getStates()));

			if (result.hasErrors()) {
				return model;
			} else {
				// maintain the coherence of one-to-one property
				UserAddress userAddress = updateBean.getUserAddress();
				userAddress.setUserObj(updateBean);
			    updateBean.setUserAddress(userAddress);
				userDelegate.updateUser(updateBean);
				model.addObject("sucessMsg","User sucessfully updated");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	/**
	 * Logout
	 * @param request
	 * @param updateBean
	 * @return
	 */
	@RequestMapping(value = ConstantUtil.URL_UPDATE, params = ConstantUtil.PARAM_LOGOUT, method = RequestMethod.POST)
	public ModelAndView backToLogin(HttpServletRequest request, @ModelAttribute(ConstantUtil.MODEL_OBJ_UPDATE_BEAN) User updateBean) {
		logger.info("Log Out of the application");
		ModelAndView model = new ModelAndView(ConstantUtil.VIEW_HOME);
		updateBean = new User();
		model.addObject(ConstantUtil.MODEL_OBJ_LOGIN_BEAN, updateBean);
		request.getSession().invalidate();
		return model;
	}
	
	 @InitBinder
	   	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder)
	   		throws ServletException {
	   		// Convert multipart object to byte[]
	   		binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
	   	}
	 
}
