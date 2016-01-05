package de.claudioaltamura.json.example;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class RawDataBindingExample {

	public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Map<String,Object> userData = new HashMap<String,Object>();
		Map<String,String> nameStruct = new HashMap<String,String>();
		nameStruct.put("first", "Joe");
		nameStruct.put("last", "Sixpack");
		userData.put("name", nameStruct);
		userData.put("gender", "MALE");
		userData.put("verified", Boolean.FALSE);
		userData.put("userImage", "Rm9vYmFyIQ==");
		mapper.writeValue(System.out, userData);
	}

}
