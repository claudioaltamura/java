package de.claudioaltamura.os.dropwizard;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.claudioaltamura.os.dropwizard.exceptionhandling.DuplicateEntryExceptionMapper;
import de.claudioaltamura.os.dropwizard.exceptionhandling.ObjectNotFoundExceptionMapper;
import de.claudioaltamura.os.dropwizard.exceptionhandling.UncaughtExceptionMapper;
import de.claudioaltamura.os.dropwizard.health.BookLibraryResourceHealtCheck;
import de.claudioaltamura.os.dropwizard.repositories.BookRepository;
import de.claudioaltamura.os.dropwizard.resources.BookResource;

public class BooklibraryApplication extends Application<BooklibraryConfiguration> {

	private static final Logger LOGGER = LoggerFactory.getLogger(BooklibraryApplication.class);

	public static void main(final String[] args) throws Exception {
		new BooklibraryApplication().run(args);
	}

	@Override
	public void run(final BooklibraryConfiguration configuration, final Environment environment) throws Exception {

		LOGGER.info("Application name: {}", configuration.getAppName());

		BookRepository bookRepository = new BookRepository();
		environment.jersey().register(new BookResource(bookRepository));

		environment.jersey().register(new ObjectNotFoundExceptionMapper());
		environment.jersey().register(new DuplicateEntryExceptionMapper());
		environment.jersey().register(new UncaughtExceptionMapper());

		environment.healthChecks().register("BookLibraryResourceHealtCheck", new BookLibraryResourceHealtCheck());

	}
}
