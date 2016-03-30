package com.cg.springmvc.service.impl;

import java.util.List;

import com.cg.springmvc.dao.UserDAO;
import com.cg.springmvc.model.State;
import com.cg.springmvc.model.User;
import com.cg.springmvc.service.UserService;

public class UserServiceImpl implements UserService {

	private UserDAO userDao;

	public UserDAO getUserDao() {
		return this.userDao;
	}

	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}

	public void addUser(User user) {
		userDao.addUser(user);
	}

	public void updateUser(User user) {
		userDao.updateUser(user);
	}

	public User getUserByUsername(String username) {
		return userDao.getUserByUsername(username);
	}

	public List<State> getStates() {
		return userDao.getStates();
	}

}
