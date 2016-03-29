package com.cg.springmvc.service.impl;

import com.cg.springmvc.dao.LoginDAO;
import com.cg.springmvc.service.LoginService;

public class LoginServiceImpl implements LoginService {

	private LoginDAO loginDao;

	public LoginDAO getLoginDao() {
		return loginDao;
	}

	public void setLoginDao(LoginDAO loginDao) {
		this.loginDao = loginDao;
	}

	@Override
	public boolean isValidUser(String username, String password) {
		return loginDao.isValidUser(username, password);
	}

	public boolean isUserIDExists(String username) {
		return loginDao.isUserIDExists(username);
	}

}
