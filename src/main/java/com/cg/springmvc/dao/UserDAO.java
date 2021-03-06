package com.cg.springmvc.dao;

import java.util.List;

import com.cg.springmvc.model.State;
import com.cg.springmvc.model.User;

public interface UserDAO {

	public void addUser(User user);

	public User updateUser(User user);

	public User getUserByUsername(String username);

	public List<State> getStates() ;



}
