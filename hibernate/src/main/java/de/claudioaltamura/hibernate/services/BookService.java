package de.claudioaltamura.hibernate.services;

import org.hibernate.Hibernate;

import de.claudioaltamura.hibernate.dao.BookDao;
import de.claudioaltamura.hibernate.entities.Book;
import de.claudioaltamura.hibernate.utils.HibernateCallback;
import de.claudioaltamura.hibernate.utils.HibernateExecuteCallback;
import de.claudioaltamura.hibernate.utils.HibernateTransactionTemplate;

public class BookService {

	private HibernateTransactionTemplate<Book> transactionTemplate; 

	private BookDao dao;
	
	public void setHibernateTemplate(HibernateTransactionTemplate<Book> transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}
	
	public void setBookDao(BookDao dao) {
		this.dao = dao;
	}
	
	public void addBook(final Book book) {
		transactionTemplate.execute(new HibernateExecuteCallback<Book>() {
			public Book execute() {
				dao.create(book);
				return book;
			}
		});
	}

	public void updateBook(final Book book) {
		transactionTemplate.execute(new HibernateExecuteCallback<Book>() {
			public Book execute() {
				dao.update(book);
				return book;
			}
		});
	}
	
	public void deleteBook(final Book book) {
		transactionTemplate.execute(new HibernateExecuteCallback<Book>() {
			public Book execute() {
				dao.delete(book);
				return book;
			}
		});
	}
	
	public Book getBook(final long id) {
		return transactionTemplate.execute(new HibernateExecuteCallback<Book>() {
			public Book execute() {
				Book book = dao.get(id);
				if(book != null)
					Hibernate.initialize(book.getAuthors());
				return book;
			}
		});
	}	
	
}
