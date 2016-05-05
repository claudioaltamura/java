package de.claudioaltamura.hibernate.services;

import java.util.List;

import org.hibernate.Hibernate;

import de.claudioaltamura.hibernate.dao.AuthorDao;
import de.claudioaltamura.hibernate.entities.Author;
import de.claudioaltamura.hibernate.utils.HibernateExecuteCallback;
import de.claudioaltamura.hibernate.utils.HibernateQueryCallback;
import de.claudioaltamura.hibernate.utils.HibernateTransactionTemplate;

public class AuthorService {

	private HibernateTransactionTemplate<Author> transactionTemplate; 

	private AuthorDao dao;
	
	public void setHibernateTemplate(HibernateTransactionTemplate<Author> transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}
	
	public void setAuthorDao(AuthorDao dao) {
		this.dao = dao;
	}

	public void addAuthor(final Author author) {
		transactionTemplate.execute(new HibernateExecuteCallback<Author>() {
			@Override
			public Author execute() {
				dao.create(author);
				return author;
			}
		});
	}
	
	public Author getAuthor(final long id) {
		return transactionTemplate.execute(new HibernateExecuteCallback<Author>() {
			@Override
			public Author execute() {
				Author author = dao.get(id);
				Hibernate.initialize(author.getBooks());
				return author;
			}			
		});
	}
	
	public List<Author> findAll() {
		return transactionTemplate.query(new HibernateQueryCallback<Author>() {
			@Override
			public List<Author> query() {
				return dao.loadAll();
			}
		});
	}
	
}
