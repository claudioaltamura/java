package de.claudioaltamura.os.jsonassert;

import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;

public class BookUtils {

	private static ObjectMapper mapper = new ObjectMapper();

	public static String toJson(Book book) {
		try {
			return mapper.writeValueAsString(book);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "";
	}

}
