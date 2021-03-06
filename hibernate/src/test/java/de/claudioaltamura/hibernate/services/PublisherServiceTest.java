package de.claudioaltamura.hibernate.services;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import de.claudioaltamura.hibernate.entities.Book;
import de.claudioaltamura.hibernate.entities.Publisher;
import de.claudioaltamura.hibernate.utils.SessionFactoryUtils;

public class PublisherServiceTest {

	private PublisherService publisherService;

	@Before
	public void setUp() {
		publisherService = new PublisherService();
		publisherService.setSessionFactory(SessionFactoryUtils.createStandardSessionFactory());
	}
	
	@Test
	public void testAddPublisher() {
		Publisher publisher = new Publisher();
		publisher.setName("Publisher 1");
		List<Book> books = new ArrayList<>();
		Book book = new Book();
		book.setTitle("My Book");
		books.add(book);
		publisher.setBooks(books);
		publisherService.addPublisher(publisher);
				
		Publisher loadedPublisher = publisherService.getPublisher(publisher.getId());
		assertEquals(publisher.getName(), loadedPublisher.getName());
		assertEquals(publisher.getBooks().size(), loadedPublisher.getBooks().size());
	}

	@Test
	public void testFindAll() {
		Publisher publisher1 = new Publisher();
		publisher1.setName("Publisher 1");
		publisherService.addPublisher(publisher1);

		Publisher publisher2 = new Publisher();
		publisher2.setName("Publisher 2");
		publisherService.addPublisher(publisher2);

		List<Publisher> allPublisher = publisherService.findAll();
		int cnt = 2;
		assertEquals(cnt, allPublisher.size());
	}

}
