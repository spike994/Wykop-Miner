package pl.poznan.put.cs.wykop.json;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JSON {
	public static final ObjectMapper mapper = new ObjectMapper();

	public static final <T> T parse(String json, Class<T> clazz) throws JsonException {
		try {
			return mapper.readValue(json, clazz);
		} catch (Exception e) {
			throw new JsonException(e);
		}
	}
	
	public static final String readErrorMsg(String errorJson) throws IOException{
		return mapper.readTree(errorJson).get("error").get("message").asText();
	} 
}
