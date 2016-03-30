package com.cg.springmvc.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cg.springmvc.model.State;
import com.cg.springmvc.model.User;

@Transactional
@Repository
public class UserDAOImpl implements UserDAO {
	
	@PersistenceContext
    private EntityManager manager;

	@Override
	public void addUser(User user) {
		manager.persist(user);
	}

	@Override
	public void updateUser(User user) {
		manager.merge(user);
	}

	@Override
	public List<State> getStates() {
		 List<State> states = manager.createQuery("Select s From State s", State.class).getResultList();
		return states;
	}

	@Override
	@SuppressWarnings("unchecked")
	public User getUserByUsername(String username) {
		List<User> list = manager.createQuery("from User u where u.username = :username and u.userAddress.addressId=u.id").setParameter("username", username).getResultList();
		return list.size() > 0 ? (User) list.get(0) : null;
	}

}
