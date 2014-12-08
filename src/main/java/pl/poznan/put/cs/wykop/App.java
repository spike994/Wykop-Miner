
        package pl.poznan.put.cs.wykop;
        import org.hibernate.Criteria;
        import org.hibernate.Session;
        import org.hibernate.Transaction;
        import org.hibernate.criterion.Projections;
        import org.hibernate.exception.ConstraintViolationException;
        import pl.poznan.put.cs.wykop.api.Api;
        import pl.poznan.put.cs.wykop.connection.ConnectionException;
        import pl.poznan.put.cs.wykop.json.JsonException;
        import pl.poznan.put.cs.wykop.model.Entry;
        import pl.poznan.put.cs.wykop.service.ConfigManager;
        import pl.poznan.put.cs.wykop.service.EntryService;
        import pl.poznan.put.cs.wykop.service.SessionManager;

        import java.io.IOException;
        import java.io.PrintWriter;

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
        Criteria criteria = session
                .createCriteria(Entry.class)
                .setProjection(Projections.min("id"));
        long lastEntry = (Long) criteria.uniqueResult();
        PrintWriter writer = new PrintWriter("ConnectionExceptionLog.txt", "UTF-8");

        for (int i =(int) lastEntry-1; i > lastEntry-500; i--) {
            transaction = session.beginTransaction();
            try {
                Entry entry = api.getEntryString(i);
                EntryService.save(entry, session);
                transaction.commit();
                System.out.println(entry.getId());
            }catch (ConnectionException e) {
                writer.println(i);
                transaction.rollback();
            }catch (ConstraintViolationException e){
                System.out.println("Constraint Violation Exception");
                transaction.rollback();
            }
        }

        writer.close();
        session.close();
    }
}


