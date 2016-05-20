package de.claudioaltamura.os.dropwizard.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import de.claudioaltamura.os.dropwizard.model.Book;
import de.claudioaltamura.os.dropwizard.repositories.BookRepository;

@Path("/books")
public class BookResource {
	
	private BookRepository bookRepository;

	public BookResource(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@PathParam("id") long id) {
		Book book = bookRepository.load(id);

		return Response.status(Status.OK).entity(book).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addBook(Book book) {
		Book createdBook = bookRepository.create(book);
		
		return Response.status(Status.CREATED).entity(createdBook).build();
	}
}
