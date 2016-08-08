package de.claudioaltamura.os.yaml;

public class Configuration {
	
	private Server server;
	private Database database;
	
	public Server getServer() {
		return server;
	}
	
	public void setServer(Server server) {
		this.server = server;
	}
	
	public Database getDatabase() {
		return database;
	}
	
	public void setDatabase(Database database) {
		this.database = database;
	}

	@Override
	public String toString() {
		return "Configuration [server=" + server + ", database=" + database
				+ "]";
	}	

}
