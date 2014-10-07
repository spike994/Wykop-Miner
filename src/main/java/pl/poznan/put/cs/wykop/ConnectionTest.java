package pl.poznan.put.cs.wykop;

import pl.poznan.put.cs.wykop.api.Api;
import pl.poznan.put.cs.wykop.connection.ConnectionException;

public class ConnectionTest {
	public static void main(String[] args) throws ConnectionException {
		Api api = new Api("09eQK85gvG","VVqxP9eMUk");
		api.setHourLimit(11000);
		
		String entry = api.getEntryString(9729894);
		System.out.println(entry);
	}
}
