
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
        Api api = new Api(appkey, secret);
        api.setHourLimit(h);
        for (int i = 10134422; i < 10134423; i++) {
            try {
                Entry e = api.getEntryString(i);
                e.inflateTags(e.getBody());
                e.inflateReceivers(e.getBody());
                e = EntryService.save(e);
            } catch (WykopException e) {
                System.out.println("Wpis nie istnieje!");
            }
        }
    }
}


