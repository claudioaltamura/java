package de.claudioaltamura.hibernate.services;

import static org.junit.Assert.*;

import org.junit.Test;

import de.claudioaltamura.hibernate.entities.Book;
import de.claudioaltamura.hibernate.utils.SessionFactoryUtils;

public class BookServiceTestTest {

	@Test
	public void testTestAddBook() {
		BookService bookService = new BookService();
		bookService.setSessionFactory(SessionFactoryUtils.createSessionFactory(Book.class));

		Book book = new Book();
		book.setTitle("My Book");
		bookService.addBook(book);

		Book loadedBook = bookService.getBook(book.getId());
		assertEquals(book.getTitle(), loadedBook.getTitle());
	}

}
