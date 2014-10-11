package pl.poznan.put.cs.wykop;

import pl.poznan.put.cs.wykop.api.Api;
import pl.poznan.put.cs.wykop.connection.ConnectionException;
import pl.poznan.put.cs.wykop.connection.WykopException;
import pl.poznan.put.cs.wykop.json.JsonException;
import pl.poznan.put.cs.wykop.model.Entry;
import pl.poznan.put.cs.wykop.service.EntryService;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

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

     /*   Employee empl = new Employee("Jack", "Bauer", new Date(System.currentTimeMillis()), "911");
        empl = save(empl);
        empl = read(empl.getId());
        System.out.printf("%d %s %s \n", empl.getId(), empl.getFirstname(), empl.getLastname());*/
        for (int i = 13202; i < 9000000; i++) {
            try {
                Entry e = api.getEntryString(i);
                e = EntryService.save(e);
                System.out.println(e);
            } catch (WykopException e) {
                System.out.println("Wpis nie istnieje!");
            }
        }

    }
}
