package de.claudioaltamura.hibernate.services;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import de.claudioaltamura.hibernate.entities.Publisher;
import de.claudioaltamura.hibernate.utils.HibernateCallback;
import de.claudioaltamura.hibernate.utils.HibernateTransactionTemplate;

public class PublisherService {

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public void addPublisher(final Publisher publisher) {
		HibernateTransactionTemplate hibernateTemplate = new HibernateTransactionTemplate(sessionFactory);
		hibernateTemplate.execute(new HibernateCallback<Publisher>() {
			@Override
			public Publisher doInHibernate(Session session) {
				session.saveOrUpdate(publisher);
				return publisher;
			}
		});
	}
	
	public Publisher getPublisher(final long id) {
		HibernateTransactionTemplate hibernateTemplate = new HibernateTransactionTemplate(sessionFactory);
		return hibernateTemplate.execute(new HibernateCallback<Publisher>() {

			@Override
			public Publisher doInHibernate(Session session) {
				
				Query query = session.createQuery("SELECT p FROM Publisher p WHERE p.id=:id");
				query.setParameter("id", id);
				Publisher publisher = (Publisher) query.uniqueResult();
				Hibernate.initialize(publisher.getBooks());
				return publisher;
			}			
		});
	}
	
	public List<Publisher> findAll() {
		HibernateTransactionTemplate hibernateTemplate = new HibernateTransactionTemplate(sessionFactory);
		return hibernateTemplate.execute(new HibernateCallback<List<Publisher>>() {

			@SuppressWarnings("unchecked")
			@Override
			public List<Publisher> doInHibernate(Session session) {
				Query query = session.createQuery("FROM Publisher");
				return query.list();
			}
		});
	}
	
}
