package de.claudioaltamura.os.dropwizard.exceptionhandling;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

public class UncaughtExceptionMapper implements ExceptionMapper<Throwable> {
		
	@Override
	public Response toResponse(Throwable exception) {
		
		return Response.status(Status.INTERNAL_SERVER_ERROR)
				.entity(new ErrorMessage(Status.INTERNAL_SERVER_ERROR.getStatusCode(), exception.getMessage()))
				.type(MediaType.APPLICATION_JSON)
				.build();
	}	

}