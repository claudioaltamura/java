package de.claudioaltamura.json.example;

import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;

public class JsonOutputter {
	
	public static void main(String[] args) {
		 
		User user = new User();
		ObjectMapper mapper = new ObjectMapper();
	 
			try {
				System.out.println(mapper.writeValueAsString(user));
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
}
