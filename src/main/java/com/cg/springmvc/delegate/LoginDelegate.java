package com.cg.springmvc.delegate;

import com.cg.springmvc.service.LoginService;

public class LoginDelegate {
	private LoginService loginService;

	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

	public boolean isValidUser(String username, String password) {
		return loginService.isValidUser(username, password);
	}

	public boolean isUserIDExists(String username) {
		return loginService.isUserIDExists(username);
	}
}
