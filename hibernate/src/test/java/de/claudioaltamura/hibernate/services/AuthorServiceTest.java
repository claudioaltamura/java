package de.claudioaltamura.hibernate.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;

import de.claudioaltamura.hibernate.dao.impl.AuthorDaoImpl;
import de.claudioaltamura.hibernate.entities.Author;
import de.claudioaltamura.hibernate.entities.Book;
import de.claudioaltamura.hibernate.utils.HibernateTransactionTemplate;
import de.claudioaltamura.hibernate.utils.SessionFactoryUtils;

public class AuthorServiceTest {

	private AuthorService authorService;
	private SessionFactory sessionFactory;

	@Before
	public void setUp() {
		sessionFactory = SessionFactoryUtils.createStandardSessionFactory();
		AuthorDaoImpl dao = new AuthorDaoImpl();
		dao.setSessionFactory(sessionFactory);
		HibernateTransactionTemplate<Author> transactionTemplate = new HibernateTransactionTemplate<Author>(sessionFactory);

		authorService = new AuthorService();
		authorService.setHibernateTemplate(transactionTemplate);
		authorService.setAuthorDao(dao);
	}
	
	@Test
	public void add() {
		Author author = new Author();
		author.setName("My author");
		
		Book book1 = new Book();
		book1.setTitle("Book 1");
		Book book2 = new Book();
		book2.setTitle("Book 2");
		List<Book> books = new ArrayList<Book>();
		books.add(book1);
		books.add(book2);
		
		author.setBooks(books);
		authorService.addAuthor(author);
		
		Author loadedAuthor = authorService.getAuthor(author.getId());
		assertNotNull(loadedAuthor.getId());
		
		assertTrue(loadedAuthor.getBooks().size()==2);
	}
	
	@Test
	public void find() {
		Author author = new Author();
		author.setName("My author");
		authorService.addAuthor(author);
		
		Author author2 = new Author();
		author2.setName("My author2");		
		authorService.addAuthor(author2);
		
		assertTrue(authorService.findAll().size()==2);
	}
	
}
