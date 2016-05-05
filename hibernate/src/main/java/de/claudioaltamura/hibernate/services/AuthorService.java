package de.claudioaltamura.hibernate.services;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import de.claudioaltamura.hibernate.entities.Author;
import de.claudioaltamura.hibernate.utils.HibernateCallback;
import de.claudioaltamura.hibernate.utils.HibernateTransactionTemplate;

public class AuthorService {

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public void addAuthor(final Author author) {
		HibernateTransactionTemplate hibernateTemplate = new HibernateTransactionTemplate(sessionFactory);
		hibernateTemplate.execute(new HibernateCallback<Author>() {
			@Override
			public Author doInHibernate(Session session) {
				session.persist(author);
				return author;
			}
		});
	}
	
	public Author getAuthor(final long id) {
		HibernateTransactionTemplate hibernateTemplate = new HibernateTransactionTemplate(sessionFactory);
		return hibernateTemplate.execute(new HibernateCallback<Author>() {

			@Override
			public Author doInHibernate(Session session) {
				
				Query query = session.createQuery("SELECT a FROM Author a WHERE a.id=:id");
				query.setParameter("id", id);
				Author author = (Author) query.uniqueResult();
				Hibernate.initialize(author.getBooks());
				return author;
			}			
		});
	}
	
	public List<Author> findAll() {
		HibernateTransactionTemplate hibernateTemplate = new HibernateTransactionTemplate(sessionFactory);
		return hibernateTemplate.execute(new HibernateCallback<List<Author>>() {

			@SuppressWarnings("unchecked")
			@Override
			public List<Author> doInHibernate(Session session) {
				Query query = session.createQuery("FROM Author");
				return query.list();
			}
		});
	}
	
}
