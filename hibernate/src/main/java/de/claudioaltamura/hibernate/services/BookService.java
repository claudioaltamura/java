package de.claudioaltamura.hibernate.services;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import de.claudioaltamura.hibernate.entities.Book;
import de.claudioaltamura.hibernate.utils.HibernateCallback;
import de.claudioaltamura.hibernate.utils.HibernateTransactionTemplate;

public class BookService {

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public void addBook(final Book book) {
		HibernateTransactionTemplate hibernateTemplate = new HibernateTransactionTemplate(sessionFactory);
		hibernateTemplate.execute(new HibernateCallback<Book>() {
			@Override
			public Book doInHibernate(Session session) {
				session.saveOrUpdate(book);
				return book;
			}
		});
	}

	public void updateBook(final Book book) {
		HibernateTransactionTemplate hibernateTemplate = new HibernateTransactionTemplate(sessionFactory);
		hibernateTemplate.execute(new HibernateCallback<Book>() {
			@Override
			public Book doInHibernate(Session session) {
				session.saveOrUpdate(book);
				return book;
			}
		});
	}
	
	public void deleteBook(final Book book) {
		HibernateTransactionTemplate hibernateTemplate = new HibernateTransactionTemplate(sessionFactory);
		hibernateTemplate.execute(new HibernateCallback<Book>() {
			@Override
			public Book doInHibernate(Session session) {
				session.delete(book);
				return book;
			}
		});
	}
	
	public Book getBook(final long id) {
		HibernateTransactionTemplate hibernateTemplate = new HibernateTransactionTemplate(sessionFactory);
		return hibernateTemplate.execute(new HibernateCallback<Book>() {

			@Override
			public Book doInHibernate(Session session) {
				//Query Cache
				//Book book = (Book) session.get(Book.class, id );
				
				Query query = session.createQuery("SELECT b FROM Book b WHERE b.id=:id");
				query.setParameter("id", id);
				Book book = (Book) query.uniqueResult();
				
//				Book book = (Book) session.createCriteria(Book.class)
//						.add( Restrictions.eq( "id", id ))
//						.uniqueResult();
				
				//Hibernate.initialize(book.getAuthors());
				return book;
			}
			
		});
	}	
	
}
