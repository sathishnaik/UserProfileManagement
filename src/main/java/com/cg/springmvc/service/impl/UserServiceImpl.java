package com.cg.springmvc.service.impl;

import java.util.List;

import com.cg.springmvc.dao.UserDAO;
import com.cg.springmvc.model.State;
import com.cg.springmvc.model.User;
import com.cg.springmvc.model.UserAddress;
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
		// maintain the coherence of one-to-one property
		UserAddress userAddress = user.getUserAddress();
		userAddress.setUserObj(user);
		user.setUserAddress(userAddress);
		userDao.addUser(user);
	}

	public User updateUser(User user) {
		return userDao.updateUser(user);
	}

	public User getUserByUsername(String username) {
		return userDao.getUserByUsername(username);
	}

	public List<State> getStates() {
		return userDao.getStates();
	}

}
