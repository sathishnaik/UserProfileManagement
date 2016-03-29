package com.cg.springmvc.dao;

import java.util.List;

import com.cg.springmvc.model.State;
import com.cg.springmvc.model.User;

public interface UserDAO {

	public void addUser(User user);

	public void updateUser(User user);

	public User getUserById(Integer userId);

	public User getUserByUsername(String username);

	public void removeUser(Integer id);
	
	public List<State> getStates() ;



}
