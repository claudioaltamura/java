package de.claudioaltamura.json.example;

public class SessionDetails {
	
	private int sessionTime;
	private int pageImpressions;
	private String userAgent;
	
	public int getSessionTime() {
		return sessionTime;
	}
	public void setSessionTime(int sessionTime) {
		this.sessionTime = sessionTime;
	}
	public int getPageImpressions() {
		return pageImpressions;
	}
	public void setPageImpressions(int pageImpressions) {
		this.pageImpressions = pageImpressions;
	}
	public String getUserAgent() {
		return userAgent;
	}
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
	@Override
	public String toString() {
		return "SessionDetails [sessionTime=" + sessionTime
				+ ", pageImpressions=" + pageImpressions + ", userAgent="
				+ userAgent + "]";
	}
	
	

}
