package pl.poznan.put.cs.wykop;

import pl.poznan.put.cs.wykop.api.Api;
import pl.poznan.put.cs.wykop.connection.ConnectionException;
import pl.poznan.put.cs.wykop.json.JsonException;
import pl.poznan.put.cs.wykop.model.Entry;

public class ConnectionTest {
	public static void main(String[] args) throws ConnectionException, JsonException {
		Api api = new Api("09eQK85gvG","VVqxP9eMUk");
		api.setHourLimit(11000);
		
		Entry entry = api.getEntryString(9729894);
		System.out.println(entry);
	}
}
