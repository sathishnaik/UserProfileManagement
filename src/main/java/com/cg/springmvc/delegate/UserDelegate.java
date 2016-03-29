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

	public void removeUser(Integer id) {
		userService.removeUser(id);
	}

	public void updateUser(User user) {
		userService.updateUser(user);
	}

	public User getUserById(Integer userId) {
		return userService.getUserById(userId);
	}

	public User getUserByUsername(String username) {
		return userService.getUserByUsername(username);
	}

	public List<State> getStates() {
		return userService.getStates();
	}
}
