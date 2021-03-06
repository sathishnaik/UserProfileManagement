package com.cg.springmvc.service;

import java.util.List;

import com.cg.springmvc.model.State;
import com.cg.springmvc.model.User;

/**
 * @author Sathish
 * 
 */
public interface UserService {
	public void addUser(User user);

	public User updateUser(User user);

	public User getUserByUsername(String username);

	public List<State> getStates();

}
