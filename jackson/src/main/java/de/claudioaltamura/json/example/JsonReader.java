package de.claudioaltamura.json.example;

import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;

public class JsonReader {

	public static void main(String[] args) {
		ObjectMapper mapper = new ObjectMapper();
		
		String json = "{\"age\":1,\"messages\":[\"msg 1\"],\"name\":\"da\"}";
		try {
			User user = mapper.readValue(json, User.class);
			System.out.println(user);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
