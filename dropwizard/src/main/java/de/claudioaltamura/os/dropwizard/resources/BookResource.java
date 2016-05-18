package de.claudioaltamura.os.dropwizard.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import de.claudioaltamura.os.dropwizard.model.Book;
import de.claudioaltamura.os.dropwizard.repositories.BookRepository;

@Path("/book")
@Produces(MediaType.APPLICATION_JSON)
public class BookResource {
	
	private BookRepository bookRepository;
	private Book bookNotFound;

	public BookResource(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
		this.bookNotFound = new Book();
	}

	@GET
	@Path("{id}")
	public Book get(@PathParam("id") long id) {
		return bookRepository.load(id).orElse(bookNotFound);
	}
}
