package de.claudioaltamura.hibernate.utils;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class HibernateTransactionTemplate<T> {

	private SessionFactory sessionFactory;
	
	public HibernateTransactionTemplate(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public T execute(HibernateExecuteCallback<T> hibernateCallback) {
		Session session = sessionFactory.openSession();

		T ret = null;
		try {
			checkStartTransaction(session);
			ret = hibernateCallback.execute();
     		session.getTransaction().commit();
		}
   		catch (HibernateException ex) {
   			session.getTransaction().rollback();
   			throw new RuntimeException(ex);
     	}
     	finally {
     		session.close();
     	}
		
		return ret;
	
	}

	public List<T> query(HibernateQueryCallback<T> hibernateCallback) {
		Session session = sessionFactory.openSession();

		List<T> ret = null;
		try {
			checkStartTransaction(session);
			ret = hibernateCallback.query();
     		session.getTransaction().commit();
		}
   		catch (HibernateException ex) {
   			session.getTransaction().rollback();
   			throw new RuntimeException(ex);
     	}
     	finally {
     		session.close();
     	}
		
		return ret;
	
	}
	
	private void checkStartTransaction(Session session) {
		if(!session.getTransaction().isActive())
		{
			session.getTransaction().begin();
		}
	}

}
