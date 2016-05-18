package de.claudioaltamura.os.dropwizard.repositories;

import java.util.Optional;

import de.claudioaltamura.os.dropwizard.model.Book;

public class BookRepository {

	public Optional<Book> load(long id) {
		Book book = null;
		if(id == 1) 
		{
			book = new Book();
			book.setTitle("My Book");
		}
		
		return Optional.ofNullable(book);		
	}
	
}
