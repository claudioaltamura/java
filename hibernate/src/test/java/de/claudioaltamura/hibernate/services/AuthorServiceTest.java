package de.claudioaltamura.hibernate.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import de.claudioaltamura.hibernate.entities.Author;
import de.claudioaltamura.hibernate.entities.Book;
import de.claudioaltamura.hibernate.utils.SessionFactoryUtils;

public class AuthorServiceTest {

	private AuthorService authorService;

	@Before
	public void setUp() {
		authorService = new AuthorService();
		authorService.setSessionFactory(SessionFactoryUtils.createStandardSessionFactory());
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
}
