package de.claudioaltamura.hibernate.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;

import de.claudioaltamura.hibernate.entities.Author;
import de.claudioaltamura.hibernate.entities.Book;
import de.claudioaltamura.hibernate.entities.Publisher;
import de.claudioaltamura.hibernate.utils.SessionFactoryUtils;

public class BookServiceTest {

	private BookService bookService;
	private SessionFactory sessionFactory;
	
	@Before
	public void setUp() {
		bookService = new BookService();
		sessionFactory = SessionFactoryUtils.createStandardSessionFactory();
		bookService.setSessionFactory(sessionFactory);
	}
	
	@Test
	public void add() {
		Book book = new Book();
		book.setTitle("My Book");
		
		Publisher publisher = new Publisher();
		publisher.setName("My Publisher");
		book.setPublisher(publisher);
		
		Author author1 = new Author();
		author1.setName("Author 1");
		Author author2 = new Author();
		author2.setName("Author 2");
		List<Author> authors = new ArrayList<Author>();
		authors.add(author1);
		authors.add(author2);
		book.setAuthors(authors);

		bookService.addBook(book);

		Book loadedBook = bookService.getBook(book.getId());
		assertEquals(book.getTitle(), loadedBook.getTitle());
		assertEquals(book.getAuthors().size(), loadedBook.getAuthors().size());
	}

	@Test
	public void update() {
		Book book = new Book();
		book.setTitle("My Book2");
		Publisher publisher = new Publisher();
		publisher.setName("My Publisher");
		book.setPublisher(publisher);
		bookService.addBook(book);

		Book loadedBook = bookService.getBook(book.getId());
		loadedBook.setTitle("My New Book2");
		bookService.updateBook(loadedBook);
		
		Book updatedBook = bookService.getBook(book.getId());
		assertEquals(loadedBook.getTitle(), updatedBook.getTitle());
	}
	
	@Test
	public void delete() {
		Book book = new Book();
		book.setTitle("My Book");
		Publisher publisher = new Publisher();
		publisher.setName("My Publisher");
		book.setPublisher(publisher);
		bookService.addBook(book);

		bookService.deleteBook(book);
		
		Book loadedBook = bookService.getBook(book.getId());
		assertNull(loadedBook);
		
		PublisherService publisherService = new PublisherService();
		publisherService.setSessionFactory(sessionFactory);
		assertTrue(publisherService.findAll().size()==0);
	}
	
	@Test(expected=RuntimeException.class)
	public void uniqueTitle() {
		Book book = new Book();
		book.setTitle("My Book");
		bookService.addBook(book);

		Book thesamebook = new Book();
		thesamebook.setTitle("My Book");
		bookService.addBook(thesamebook);
	}

	@Test(expected=RuntimeException.class)
	public void tooLongTitle() {
		Book book = new Book();
		String longTitle = "";
		for(int i = 0; i < 100; i++)
			longTitle += Integer.toString(i);
		book.setTitle(longTitle);
		bookService.addBook(book);
	}
	
}
