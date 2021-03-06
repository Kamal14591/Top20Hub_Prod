package com.kj.top20hub.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kj.top20hub.dto.User;
import com.kj.top20hub.dto.UserDetails;
import com.kj.top20hub.dto.UserInterest;

@Repository
public class UserDaoImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;
		
	@Override
	public List<User> getAlluser() {

		Session currentSession = null;
		currentSession = sessionFactory.getCurrentSession();
		Query<User> query = currentSession.createQuery("from User", User.class);
		List<User> result = query.getResultList();
		return result;
	}

	@Override
	public User getUser(int id) {
		Session curSession = null;
		curSession = sessionFactory.getCurrentSession();
		User user = curSession.get(User.class, id);
		return user;
	}
	
	@Override
	public int createUser(User user)
	{
		Session curSession = null;
		curSession = sessionFactory.getCurrentSession();
		
		UserDetails userDetails = new UserDetails();
		UserInterest userInterest = new UserInterest();
		
		
		user.setUserDetails(userDetails);
		user.setUserIntrest(userInterest);
		int result = (int) curSession.save(user);
		return result;
	}

	@Override
	public void updateUser(User user) {

		Session curSession = null;
		curSession = sessionFactory.getCurrentSession();
		curSession.update(user);
	}

	@Override
	public User deleteUser(int id) {

		Session curSession = null;
		curSession = sessionFactory.getCurrentSession();
		User user = curSession.get(User.class,id);
        curSession.delete(user);
		return user;
	}

}
