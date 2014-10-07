package pl.poznan.put.cs.wykop.pojo;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import pl.poznan.put.cs.wykop.api.Api;
import pl.poznan.put.cs.wykop.connection.ConnectionException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class App {
	public static void main(String[] args) throws IOException, ConnectionException {
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

		String json = api.getEntryString(9727380);

		ObjectMapper mapper = new ObjectMapper();
		Entry e = mapper.readValue(json, Entry.class);

		System.out.println(e);
	}
}
