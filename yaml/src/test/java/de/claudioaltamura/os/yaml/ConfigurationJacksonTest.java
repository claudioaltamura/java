package de.claudioaltamura.os.yaml;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

public class ConfigurationJacksonTest {

	private ObjectMapper objectMapper;
	private InputStream configurationFile;
	private String fileName = "configuration-new.yaml";

	@Before
	public void setUp() throws IOException {
		objectMapper = new ObjectMapper(new YAMLFactory());
		configurationFile = Configuration.class.getClassLoader().getResourceAsStream("configuration.yaml");
		
		Files.deleteIfExists(new File(fileName).toPath());
	}
	
	@Test
	public void read() throws JsonParseException, JsonMappingException, IOException {
		Configuration configuration = objectMapper.readValue(configurationFile, Configuration.class);
		assertEquals(100l, configuration.getServer().getTimeoutMs());
		//and so on
	}
	
	@Test
	public void write() throws JsonParseException, JsonMappingException, IOException {
		OutputStream outputStream = new FileOutputStream(fileName);
		Configuration configuration = objectMapper.readValue(configurationFile, Configuration.class);
		objectMapper.writeValue(outputStream, configuration);
		
		//just writing out
		File file = new File(fileName);
		assertTrue(file.exists());
	}

}
