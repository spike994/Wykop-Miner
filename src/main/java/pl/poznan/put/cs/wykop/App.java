
        package pl.poznan.put.cs.wykop;

        import pl.poznan.put.cs.wykop.api.Api;
        import pl.poznan.put.cs.wykop.connection.ConnectionException;
        import pl.poznan.put.cs.wykop.json.JsonException;
        import pl.poznan.put.cs.wykop.model.Entry;
        import pl.poznan.put.cs.wykop.model.Link;
        import pl.poznan.put.cs.wykop.service.ConfigManager;

        import java.io.IOException;

        public class App {
    public static void main(String[] args) throws IOException, ConnectionException, JsonException {

        ConfigManager configManager = ConfigManager.getConfigManager();
        String appkey = (String) configManager.getProperty("appkey");
        String secret = (String) configManager.getProperty("secret");
        int h = Integer.valueOf((String) configManager.getProperty("hour_limit"));
        Api api = new Api(appkey, secret);
        api.setHourLimit(h);
        Entry entry = api.getEntryString(8599910);
        Link link = api.getLinkString(1);

        System.out.println(link.toString());
        }
    }



