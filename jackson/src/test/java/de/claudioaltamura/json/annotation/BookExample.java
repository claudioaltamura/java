package de.claudioaltamura.json.annotation;

import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

public class BookExample {

	@Test
	public void test() {
		Book book = new Book();
		book.setName("First Book");
		ObjectMapper mapper = new ObjectMapper();
		 
		try {
			System.out.println(mapper.writeValueAsString(book));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
