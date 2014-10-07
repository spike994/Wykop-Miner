package pl.poznan.put.cs.wykop.api;

import pl.poznan.put.cs.wykop.connection.Connection;
import pl.poznan.put.cs.wykop.connection.ConnectionException;
import pl.poznan.put.cs.wykop.json.JSON;
import pl.poznan.put.cs.wykop.json.JsonException;
import pl.poznan.put.cs.wykop.model.Entry;

public class Api {
	private Connection conn;

	public Api(String appKey, String secret) {
		this.conn = new Connection(appKey, secret);
	}

	public Entry getEntryString(int i) throws ConnectionException, JsonException {
		return this.getObject("entries/index", "" + i, Entry.class);
	}

	public String getLinksCommentsString(int i) throws ConnectionException {
		return this.getJsonResponse("link/comments", "" + i);
	}

	public String getLinkString(int i) throws ConnectionException {
		return this.getJsonResponse("link/index", "" + i);
	}

	public String getProfileString(String username) throws ConnectionException {
		return this.getJsonResponse("profile/index", username);
	}

	public String getJsonResponse(String method, String param) throws ConnectionException {
		return this.conn.call(method, param);
	}

	private <T> T getObject(String method, String param, Class<T> clazz) throws ConnectionException, JsonException {
		String json = this.conn.call(method, param);
		return JSON.parse(json, clazz);
	}

	public void setHourLimit(int noOfRequest) {
		this.conn.setHourLimit(noOfRequest);
	}
}
