package com.cg.springmvc.delegate;

import java.util.List;

import com.cg.springmvc.model.State;
import com.cg.springmvc.model.User;
import com.cg.springmvc.service.UserService;

public class UserDelegate {

	private UserService userService;

	public UserService getUserService() {
		return this.userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void addUser(User user) {
		userService.addUser(user);
	}

	public User updateUser(User user) {
		return userService.updateUser(user);
	}

	public User getUserByUsername(String username) {
		return userService.getUserByUsername(username);
	}

	public List<State> getStates() {
		return userService.getStates();
	}
}
