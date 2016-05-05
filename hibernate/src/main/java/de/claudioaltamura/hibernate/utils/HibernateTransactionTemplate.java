package de.claudioaltamura.hibernate.utils;

import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import de.claudioaltamura.hibernate.utils.HibernateCallback;

public class HibernateTransactionTemplate {

	private SessionFactory sessionFactory;
	
	public HibernateTransactionTemplate(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory; 
	}
	
	public <T> T execute(HibernateCallback<T> hibernateCallback) {
		Session session = sessionFactory.openSession();

		session.getTransaction().begin();

		T ret = null;
		try {
			ret = hibernateCallback.doInHibernate(session);
     		session.getTransaction().commit();
		}
   		catch (HibernateException | SQLException ex) {
   			session.getTransaction().rollback();
   			throw new RuntimeException(ex);
     	}
     	finally {
     		session.close();
     	}
		
		return ret;
	
	}

}
