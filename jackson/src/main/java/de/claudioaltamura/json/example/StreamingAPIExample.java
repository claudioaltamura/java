package de.claudioaltamura.json.example;

import java.io.IOException;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;

public class StreamingAPIExample {

	public static void main(String[] args) {
		String json = "{\"session_details\": {\"session_time\": 34,\"page_impressions\": 2, \"user-agent\" : \"ABC\"}}";
		try {
			JsonFactory f = new JsonFactory();
			JsonParser jp = f.createJsonParser(json);
			jp.nextToken();
			SessionDetails details = new SessionDetails();
			while (jp.nextToken() != JsonToken.END_OBJECT) {
				String fieldname = jp.getCurrentName();
				if("session_time".equals(fieldname)) {
					jp.nextValue();
					details.setSessionTime(jp.getIntValue());
				}
				if("page_impressions".equals(fieldname)) {
					jp.nextValue();					
					details.setPageImpressions(jp.getIntValue());
				}
				if("user-agent".equals(fieldname)) {
					jp.nextValue();					
					details.setUserAgent(jp.getText());
				}
			}
			System.out.println(details);
		} catch (IOException e) {
			//log.error(); 
		}

	}

}
