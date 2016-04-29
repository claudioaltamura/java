package de.claudioaltamura.hibernate.services;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.claudioaltamura.hibernate.entities.Book;
import de.claudioaltamura.hibernate.entities.Publisher;
import de.claudioaltamura.hibernate.utils.SessionFactoryUtils;

public class BookServiceTest {

	private BookService bookService;

	@Before
	public void setUp() {
		bookService = new BookService();
		bookService.setSessionFactory(SessionFactoryUtils.createSessionFactory(Book.class, Publisher.class));
	}
	
	@Test
	public void add() {
		Book book = new Book();
		book.setTitle("My Book");
		Publisher publisher = new Publisher();
		publisher.setName("My Publisher");
		book.setPublisher(publisher);
		bookService.addBook(book);

		Book loadedBook = bookService.getBook(book.getId());
		assertEquals(book.getTitle(), loadedBook.getTitle());
	}

	@Test
	public void update() {
		Book book = new Book();
		book.setTitle("My Book");
		Publisher publisher = new Publisher();
		publisher.setName("My Publisher");
		book.setPublisher(publisher);
		bookService.addBook(book);

		Book loadedBook = bookService.getBook(book.getId());
		loadedBook.setTitle("My New Book");
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
	}
}
