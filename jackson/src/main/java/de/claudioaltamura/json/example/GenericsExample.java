package de.claudioaltamura.json.example;

import java.io.IOException;
import java.util.Map;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

public class GenericsExample {

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper(new JsonFactory());
		TypeReference<Map<String,String>> typeRef = new TypeReference<Map<String,String>>() {};

		String content = "{\"gender\":\"MALE\",\"verified\":false}";
		
		Map<String, String> detailsMap = 
			mapper.readValue(content, typeRef);

		System.out.println(detailsMap);
	}

}
