package de.claudioaltamura.hibernate.services;

import static org.junit.Assert.*;

import org.junit.Test;

import de.claudioaltamura.hibernate.entities.Book;

public class BookServiceTest {

	@Test
	public void testAddBook() {
		BookService bookService = new BookService();
		Book book = new Book();
		book.setTitle("My Book");
		bookService.addBook(book);
		
		Book loadedBook = bookService.getBook(book.getId());
		assertEquals(book.getTitle(), loadedBook.getTitle());
	}

}
