package com.cg.springmvc.dao;

public interface LoginDAO {
	
	public boolean isValidUser(String username, String password);
	
	public boolean isUserIDExists(String username);

}
