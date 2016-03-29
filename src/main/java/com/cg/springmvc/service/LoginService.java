package com.cg.springmvc.service;

public interface LoginService {
	
	public boolean isValidUser(String username, String password);
	
	public boolean isUserIDExists(String username);

}
