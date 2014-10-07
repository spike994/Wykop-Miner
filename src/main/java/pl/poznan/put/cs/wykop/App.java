package pl.poznan.put.cs.wykop;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import pl.poznan.put.cs.wykop.api.Api;
import pl.poznan.put.cs.wykop.connection.ConnectionException;
import pl.poznan.put.cs.wykop.json.JsonException;
import pl.poznan.put.cs.wykop.model.Entry;

public class App {
	public static void main(String[] args) throws IOException, ConnectionException, JsonException {
		Properties prop = new Properties();
		InputStream propStream = ClassLoader.getSystemResourceAsStream("wykop.properties");
		prop.load(propStream);
		String appkey = (String) prop.get("appkey");
		String secret = (String) prop.get("secret");
		int h = Integer.valueOf((String) prop.get("hour_limit"));

		// InputStream jsonStream =
		// ClassLoader.getSystemResourceAsStream("b.json");
		// String json = IOUtils.toString(jsonStream, "utf-8");

		Api api = new Api(appkey, secret);
		api.setHourLimit(h);

		Entry e = api.getEntryString(9727380);
		System.out.println(e);
	}
}
