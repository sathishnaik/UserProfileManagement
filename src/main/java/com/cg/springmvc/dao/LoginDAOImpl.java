package com.cg.springmvc.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cg.springmvc.model.User;

@Repository
@Transactional
public class LoginDAOImpl implements LoginDAO{
	
	@PersistenceContext
    private EntityManager manager;
	
	/*public LoginDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}*/

	
	@Override
	public boolean isUserIDExists(String username) {
		TypedQuery<User> query = manager.createQuery("from User u where u.username = :username",  User.class).setParameter("username", username);
		query.getFirstResult();
		query.setMaxResults(1);
		List<User> listUser = query.getResultList();
		if (listUser != null && listUser.size()>0)
			return true;
		else
			return false;
	}

	@Override
	public boolean isValidUser(String username, String password) {
		TypedQuery<User> query = manager.createQuery("select u from User u where u.username=:username and u.password=:password", User.class)
				.setParameter("username", username).
				setParameter("password", password);
		query.getFirstResult();
		query.setMaxResults(1);
		List<User> listUser = query.getResultList();
		if (listUser != null && listUser.size() > 0)
			return true;
		else
			return false;
	}

}
