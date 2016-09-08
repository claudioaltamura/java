package de.claudioaltamura.os.yaml;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStream;
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

	@Before
	public void setUp() throws IOException {
		DumperOptions options = new DumperOptions();
		options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
		yaml = new Yaml(options);
	}
	
	@Test
	public void read() {
		configurationFile = Configuration.class.getClassLoader().getResourceAsStream("configuration.yaml");

	    @SuppressWarnings("unchecked")
		Map<String, Map<String, Object>> configuration = (Map<String, Map<String, Object>>) yaml.load(configurationFile);
	    
	    assertEquals(100, configuration.get("server").get("timeoutMs"));
		//and so on
	}
	
	@Test
	public void write() throws JsonParseException, JsonMappingException, IOException {
		configurationFile = Configuration.class.getClassLoader().getResourceAsStream("configuration.yaml");

		@SuppressWarnings("unchecked")
		Map<String, Map<String, Object>> configuration = (Map<String, Map<String, Object>>) yaml.load(configurationFile);

		//just writing out
	    String output = yaml.dump(configuration);
	    assertTrue(output.contains("100"));
	}

	@Test
	public void readJavaBeans() {
		configurationFile = Configuration.class.getClassLoader().getResourceAsStream("configuration-snakeyaml.yaml");

		Configuration configuration = (Configuration) yaml.load(configurationFile);

	    assertEquals("abc", configuration.getServer().getId());
		//and so on
	}	
	
	@Test
	public void writeJavaBeans() throws JsonParseException, JsonMappingException, IOException {
		Configuration configuration = new Configuration();
		Server server = new Server();
		server.setId("abc");
		Database database = new Database();
		database.setUser("scott");
		configuration.setServer(server);
		configuration.setDatabase(database);

		//just writing out
	    String output = yaml.dump(configuration);
	    //System.out.println(output);
	}	
	
}
