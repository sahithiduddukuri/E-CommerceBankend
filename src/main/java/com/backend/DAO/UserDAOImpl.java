package com.backend.DAO;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.backend.model.User;

public class UserDAOImpl implements UserDAO{
	public UserDAOImpl()
	{
		
	}
	@Autowired
	private SessionFactory sessionFactory;
	
	public UserDAOImpl(SessionFactory sessionFactory)
	{
		this.sessionFactory=sessionFactory;
	}
	public boolean saveUser(User user)
	{
		try
		{
			Session session=sessionFactory.openSession();
			Transaction tx=session.beginTransaction();
			session.saveOrUpdate(user);
			tx.commit();
			return true;
		}catch(HibernateException e){
			e.printStackTrace();
		}
		return false;
	}
}
