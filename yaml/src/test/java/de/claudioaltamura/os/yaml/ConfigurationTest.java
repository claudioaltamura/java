package de.claudioaltamura.os.yaml;

import static org.junit.Assert.assertEquals;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

public class ConfigurationTest {

	@Test
	public void test() throws JsonParseException, JsonMappingException, IOException {
		//Reading
		ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
		InputStream inputStream = Configuration.class.getClassLoader().getResourceAsStream("configuration.yaml");
		Configuration configuration = mapper.readValue(inputStream, Configuration.class);
		assertEquals("100", configuration.getServer().getTimeoutMs());
		//and so on
		
		//Writing
		OutputStream outputStream = new FileOutputStream("configuration-new.yaml");
		mapper.writeValue(outputStream, configuration);
		//just writing out
	}

}
