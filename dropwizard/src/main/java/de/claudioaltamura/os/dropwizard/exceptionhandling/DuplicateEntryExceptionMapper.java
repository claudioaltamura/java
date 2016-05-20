package de.claudioaltamura.os.dropwizard.exceptionhandling;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

public class DuplicateEntryExceptionMapper implements ExceptionMapper<DuplicateEntryException> {

	@Override
	public Response toResponse(DuplicateEntryException exception) {
		
		return Response.status(Status.BAD_REQUEST)
				.entity(new ErrorMessage(Status.BAD_REQUEST.getStatusCode(), exception.getMessage()))
				.type(MediaType.APPLICATION_JSON)
				.build();
	}

}
