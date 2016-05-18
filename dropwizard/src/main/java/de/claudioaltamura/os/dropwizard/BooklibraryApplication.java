package de.claudioaltamura.os.dropwizard;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BooklibraryApplication extends Application<BooklibraryConfiguration> {

    public static final Logger LOGGER = LoggerFactory.getLogger(BooklibraryApplication.class);

    public static void main(final String[] args) throws Exception {
        new BooklibraryApplication().run(args);
    }

    @Override
    public void run(final BooklibraryConfiguration configuration, final Environment environment)
            throws Exception {

        LOGGER.info("Application name: {}", configuration.getAppName());
    }
}
