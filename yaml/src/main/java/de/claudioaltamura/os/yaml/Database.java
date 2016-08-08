package de.claudioaltamura.os.yaml;

public class Database {
	
	private String user;
	private String password;
	private String url;
	private String table;
	
	public String getUser() {
		return user;
	}
	
	public void setUser(String user) {
		this.user = user;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getTable() {
		return table;
	}
	
	public void setTable(String table) {
		this.table = table;
	}

	@Override
	public String toString() {
		return "Database [user=" + user + ", password=" + password + ", url="
				+ url + ", table=" + table + "]";
	}
	
}
