package de.claudioaltamura.hibernate.services;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import de.claudioaltamura.hibernate.entities.Book;

public class BookService {

	private static SessionFactory sessionFactory = null;
	
	public void addBook(Book book) {
		Session session = openSession();
		session.getTransaction().begin();
		session.persist(book);
		session.getTransaction().commit();
	}
	
	public Book getBook(long id) {
		Session session = openSession();
		
//		Book book = (Book) session.get(Book.class, id );
		
		Query query = session.createQuery("SELECT b FROM Book b WHERE b.id=:id");
		query.setParameter("id", id);
		Book book = (Book) query.uniqueResult();
		
//		Book book = (Book) session.createCriteria(Book.class)
//				.add( Restrictions.eq( "id", id ))
//				.uniqueResult();
		
		//Hibernate.initialize(book.getAuthors());
		
		session.close();
		
		return book;
	}	
	
	private static Session openSession() {
		if (sessionFactory == null) {
			final Configuration configuration = new Configuration();
			configuration.addAnnotatedClass( Book.class );
			
			sessionFactory = configuration.buildSessionFactory( new StandardServiceRegistryBuilder().build() );
		}
		return sessionFactory.openSession();
	}
}
