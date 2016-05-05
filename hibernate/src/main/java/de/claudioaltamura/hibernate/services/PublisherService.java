package de.claudioaltamura.hibernate.services;

import java.util.List;

import org.hibernate.Hibernate;

import de.claudioaltamura.hibernate.dao.PublisherDao;
import de.claudioaltamura.hibernate.entities.Publisher;
import de.claudioaltamura.hibernate.utils.HibernateExecuteCallback;
import de.claudioaltamura.hibernate.utils.HibernateQueryCallback;
import de.claudioaltamura.hibernate.utils.HibernateTransactionTemplate;

public class PublisherService {

	private HibernateTransactionTemplate<Publisher> transactionTemplate; 

	private PublisherDao dao;
	
	public void setHibernateTemplate(HibernateTransactionTemplate<Publisher> transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}
	
	public void setPublisherDao(PublisherDao dao) {
		this.dao = dao;
	}
	
	public void addPublisher(final Publisher publisher) {
		transactionTemplate.execute(new HibernateExecuteCallback<Publisher>() {
			public Publisher execute() {
				dao.create(publisher);
				return publisher;
			}
		});
	}
	
	public Publisher getPublisher(final long id) {
		return transactionTemplate.execute(new HibernateExecuteCallback<Publisher>() {
			public Publisher execute() {
				Publisher publisher = dao.get(id);
				if(publisher != null)
					Hibernate.initialize(publisher.getBooks());
				return publisher;
			}
		});
	}
	
	public List<Publisher> findAll() {
		return transactionTemplate.query(new HibernateQueryCallback<Publisher>() {
			public List<Publisher> query() {
				return dao.loadAll();
			}
		});
	}	
}
