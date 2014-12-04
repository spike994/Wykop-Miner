
        package pl.poznan.put.cs.wykop;
        import org.hibernate.Session;
        import org.hibernate.SessionFactory;
        import org.hibernate.Transaction;
        import org.hibernate.exception.ConstraintViolationException;
        import pl.poznan.put.cs.wykop.api.Api;
        import pl.poznan.put.cs.wykop.connection.ConnectionException;
        import pl.poznan.put.cs.wykop.connection.WykopException;
        import pl.poznan.put.cs.wykop.json.JsonException;
        import pl.poznan.put.cs.wykop.model.Entry;
        import pl.poznan.put.cs.wykop.service.ConfigManager;
        import pl.poznan.put.cs.wykop.service.EntryService;
        import pl.poznan.put.cs.wykop.service.SessionManager;
        import pl.poznan.put.cs.wykop.util.HibernateUtil;

        import java.io.IOException;
public class App {
    public static void main(String[] args) throws IOException, ConnectionException, JsonException {

        ConfigManager configManager = ConfigManager.getConfigManager();
        String appkey = (String) configManager.getProperty("appkey");
        String secret = (String) configManager.getProperty("secret");
        int h = Integer.valueOf((String) configManager.getProperty("hour_limit"));
        Api api = new Api(appkey, secret);
        Session session = SessionManager.getSessionManager().getSession();
        api.setHourLimit(h);
        Transaction transaction;
        for (int i = 10383624; i < 10383625; i++) {
            transaction = session.beginTransaction();
            try {
                Entry entry = api.getEntryString(i);
                EntryService.save(entry, session);
                transaction.commit();
            } catch (WykopException e) {
                transaction.rollback();
//                System.out.println("Wpis nie istnieje!");
            } catch (ConstraintViolationException e){
                transaction.rollback();
            }
        }
        session.close();
    }
}


