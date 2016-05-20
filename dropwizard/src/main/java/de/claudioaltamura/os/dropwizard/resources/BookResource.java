package de.claudioaltamura.os.dropwizard.resources;

import java.net.URI;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import de.claudioaltamura.os.dropwizard.model.Book;
import de.claudioaltamura.os.dropwizard.repositories.BookRepository;

@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource {

	private BookRepository bookRepository;

	public BookResource(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	@GET
	@Path("{id}")
	public Response get(@PathParam("id") Long id) {
		Book book = bookRepository.load(id);

		return Response.ok().entity(book).build();
	}

	@POST
	public Response addBook(@Valid Book book, @Context UriInfo uriInfo) {
		Book createdBook = bookRepository.create(book);

		URI uri = uriInfo.getAbsolutePathBuilder()
				.path(String.valueOf(createdBook.getId())).build();
		return Response.created(uri).entity(createdBook).build();
	}
}
