package com.backend.DAO;



import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.backend.model.Category;
import com.backend.model.Supplier;
import com.backend.DAO.CategoryDAO;
@Repository
@Service
public class CategoryDAOImpl implements CategoryDAO
{

	@Autowired
	SessionFactory sessionFactory;
	
	public CategoryDAOImpl(SessionFactory sessionFactory)
	{
		this.sessionFactory=sessionFactory;
	}

	
	
	public void insertCategory(Category category)
	{
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.persist(category);
		session.getTransaction().commit();
		
	}
	
	
	public List<Category> retrieve()
	{
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List<Category> li = session.createQuery("from Category").list();
		  
		session.getTransaction().commit();
		return li;
		
	}
	
	public Category findByCatId(int cid)
	{
		Session session=sessionFactory.openSession();
		Category c=null;
		try
		{
			session.beginTransaction();
			c= session.get(Category.class, cid); 
			session.getTransaction().commit();
		}
		catch(HibernateException e)
		{
			System.out.println(e.getMessage());
			session.getTransaction().rollback();
		}
		return c;
	}
}