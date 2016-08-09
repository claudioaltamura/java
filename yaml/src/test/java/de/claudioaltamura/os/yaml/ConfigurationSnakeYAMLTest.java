package de.claudioaltamura.os.yaml;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class ConfigurationSnakeYAMLTest {
	
	private Yaml yaml;
	private InputStream configurationFile;
	private String fileName = "configuration-new.yaml";

	@Before
	public void setUp() throws IOException {
		DumperOptions options = new DumperOptions();
		options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
		yaml = new Yaml(options);
		configurationFile = Configuration.class.getClassLoader().getResourceAsStream("configuration.yaml");

		Files.deleteIfExists(new File(fileName).toPath());
	}
	
	
	@Test
	public void read() {
	    @SuppressWarnings("unchecked")
		Map<String, Map<String, Object>> configuration = (Map<String, Map<String, Object>>) yaml.load(configurationFile);
	    
	    assertEquals(100, configuration.get("server").get("timeoutMs"));
		//and so on
	}
	
	@Test
	public void write() throws JsonParseException, JsonMappingException, IOException {
	    @SuppressWarnings("unchecked")
		Map<String, Map<String, Object>> configuration = (Map<String, Map<String, Object>>) yaml.load(configurationFile);

		//just writing out
	    String output = yaml.dump(configuration);
	    assertTrue(output.contains("100"));
	}

}
