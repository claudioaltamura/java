package de.claudioaltamura.hibernate.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class AbstractDao<T> implements GenericDao<T> {
	
    private Class<T> clazz;
    
    private SessionFactory sessionFactory;
    
    @SuppressWarnings("unchecked")
	public AbstractDao() {
        clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }    
    
    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<T> loadAll() {
		Session session = sessionFactory.getCurrentSession();
		checkStartTransaction(session);
		Query query = session.createQuery("FROM " + clazz.getSimpleName());
		return query.list();
	}


	@Override
	public void create(T object) {
		Session session = sessionFactory.getCurrentSession();
		checkStartTransaction(session);
		session.persist(object);
	}

	@Override
	public void update(T object) {
		Session session = sessionFactory.getCurrentSession();
		checkStartTransaction(session);
		session.saveOrUpdate(object);
	}

	@Override
	public void delete(T object) {
		Session session = sessionFactory.getCurrentSession();
		checkStartTransaction(session);
		session.delete(object);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T get(Serializable id) {
		Session session = sessionFactory.getCurrentSession();
		checkStartTransaction(session);
		return (T) session.get(clazz, id);
	}

	private void checkStartTransaction(Session session) {
		if(!session.getTransaction().isActive())
		{
			session.getTransaction().begin();
		}
	}

}
