package com.cg.springmvc.controller;

import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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

import com.cg.springmvc.delegate.UserDelegate;
import com.cg.springmvc.model.User;
import com.cg.springmvc.model.UserAddress;
import com.cg.springmvc.utils.ConstantUtil;
import com.cg.springmvc.utils.UserProfileUtil;
import com.cg.springmvc.validator.UpdateValidator;

@Controller
public class UserController {

	@Autowired
	private UserDelegate userDelegate;

	@Autowired
	UpdateValidator updateValidator;

	/**
	 * update user object
	 * @param request
	 * @param response
	 * @param updateBean
	 * @param result
	 * @param file
	 * @param status
	 * @param map
	 * @return
	 */
	@RequestMapping(value = ConstantUtil.URL_UPDATE, params = ConstantUtil.PARAM_UPDATE, method = RequestMethod.POST)
	public ModelAndView userUpdate(HttpServletRequest request,
			HttpServletResponse response,
			@Valid @ModelAttribute(ConstantUtil.MODEL_OBJ_UPDATE_BEAN) User updateBean,
			BindingResult result, @RequestParam(ConstantUtil.PARAM_IMAGE) MultipartFile file,
			SessionStatus status, Map<String, Object> map) {
		ModelAndView model = new ModelAndView(ConstantUtil.VIEW_UPDATE);
		try {
			updateValidator.validate(updateBean, result);
			User user = userDelegate.getUserByUsername(updateBean.getUsername());
			
			String filename = file.getOriginalFilename();
			String base64Encoded = null;
			if (filename.isEmpty()) {
				byte[] encodeBase64 = Base64.encodeBase64(user.getImage());
				base64Encoded = new String(encodeBase64, ConstantUtil.UTF_8);
				updateBean.setImage(user.getImage());
			} else {
				byte[] encodeBase64 = Base64.encodeBase64(updateBean.getImage());
				base64Encoded = new String(encodeBase64, ConstantUtil.UTF_8);
			}
			model.addObject(ConstantUtil.USER_IMAGE, base64Encoded);
			model.addObject(ConstantUtil.MODEL_OBJ_UPDATE_BEAN, updateBean);
			map.put(ConstantUtil.STATES, UserProfileUtil.getStates(userDelegate.getStates()));

			if (result.hasErrors()) {
				return model;
			} else {
				// if username is updated then overwrite username session attribute
				UserAddress userAddress = updateBean.getUserAddress();
				userAddress.setUserObj(updateBean);
			    updateBean.setUserAddress(userAddress);
				userDelegate.updateUser(updateBean);
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
