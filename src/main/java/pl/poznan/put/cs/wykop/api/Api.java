package pl.poznan.put.cs.wykop.api;

import pl.poznan.put.cs.wykop.connection.Connection;
import pl.poznan.put.cs.wykop.connection.ConnectionException;

public class Api {
	private Connection conn;

	public Api(String appKey, String secret) {
		this.conn = new Connection(appKey, secret);
	}

	public String getEntryString(int i) throws ConnectionException {
		return this.getResponse("entries/index", "" + i);
	}

	public String getLinksCommentsString(int i) throws ConnectionException {
		return this.getResponse("link/comments", "" + i);
	}

	public String getLinkString(int i) throws ConnectionException {
		return this.getResponse("link/index", "" + i);
	}

	public String getProfileString(String username) throws ConnectionException {
		return this.getResponse("profile/index", username);
	}

	private String getResponse(String method, String param) throws ConnectionException {
		return this.conn.call(method, param);
	}

	public void setHourLimit(int noOfRequest) {
		this.conn.setHourLimit(noOfRequest);
	}
}
