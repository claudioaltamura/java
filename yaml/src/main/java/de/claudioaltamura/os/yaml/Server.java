package de.claudioaltamura.os.yaml;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Server {

	public enum Type {
		@JsonProperty("simple")
		SIMPLE, 
		@JsonProperty("complex")
		COMPLEX;
	}	
	
	private Type type;
	private String id;
	private long timeoutMs;

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getTimeoutMs() {
		return timeoutMs;
	}

	public void setTimeoutMs(long timeoutMs) {
		this.timeoutMs = timeoutMs;
	}

	@Override
	public String toString() {
		return "Server [type=" + type + ", id=" + id + ", timeoutMs="
				+ timeoutMs + "]";
	}	
	
}
