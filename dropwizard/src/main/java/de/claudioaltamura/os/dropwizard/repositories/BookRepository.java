package de.claudioaltamura.os.dropwizard.repositories;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import de.claudioaltamura.os.dropwizard.exceptionhandling.DuplicateEntryException;
import de.claudioaltamura.os.dropwizard.exceptionhandling.ObjectNotFoundException;
import de.claudioaltamura.os.dropwizard.model.Book;

public class BookRepository {
	
	private Map<Long, Book> books = new HashMap<>();

	public BookRepository() {
		initRepository();
	}
	
	private void initRepository() {
		Book book = new Book();
		book.setId(Long.valueOf(1));
		book.setTitle("My Book");
		
		books.put(book.getId(), book);
	}

	public Book load(long id) throws ObjectNotFoundException {
		Book book = books.get(Long.valueOf(id));
		
		if(book == null)
			throw new ObjectNotFoundException("book with id " + id + " not found!");
		
		return book;
	}

	public Book create(Book book) throws RuntimeException {
		if(book.getId() != null) {
			if(books.containsKey(book.getId()))
				throw new DuplicateEntryException("book with id " + book.getId() + " already exists!");
		} else {
			
			long max = findMax(books.keySet());
			book.setId(max + 1);
		}
		
		books.put(book.getId(), book);
		
		return book;
	}

	private long findMax(Set<Long> keySet) {
		long max = 0;
		for (Long key : keySet) {
			long keyValue = key.longValue();
			if(keyValue > max)
				max = keyValue;
		}

		return max;
	}
	
}
