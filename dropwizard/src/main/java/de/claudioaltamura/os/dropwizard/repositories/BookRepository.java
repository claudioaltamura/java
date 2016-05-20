package de.claudioaltamura.os.dropwizard.repositories;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import de.claudioaltamura.os.dropwizard.model.Book;

public class BookRepository {
	
	private Map<Long, Book> books = new HashMap<>();

	public BookRepository() {
		Book book = null;

		book = new Book();
		book.setTitle("My Book");
		
		books.put(Long.valueOf(1), book);
	}
	
	public Optional<Book> load(long id) {
		return Optional.ofNullable(books.get(Long.valueOf(id)));		
	}

	public Book save(Book book) {
		return books.put(book.getId(), book);
	}
	
}
