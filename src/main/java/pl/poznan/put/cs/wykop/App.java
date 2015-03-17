package pl.poznan.put.cs.wykop;

import org.apache.commons.io.FileUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.exception.ConstraintViolationException;
import pl.poznan.put.cs.wykop.api.Api;
import pl.poznan.put.cs.wykop.connection.ConnectionException;
import pl.poznan.put.cs.wykop.connection.WykopException;
import pl.poznan.put.cs.wykop.json.JsonException;
import pl.poznan.put.cs.wykop.model.Entry;
import pl.poznan.put.cs.wykop.service.ConfigManager;
import pl.poznan.put.cs.wykop.service.EntryService;
import pl.poznan.put.cs.wykop.service.SessionManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        List<Integer> ids= getMissedEntries("ConnectionExceptionLog.txt");
        for (Integer arg : ids) {
            transaction = session.beginTransaction();
            try {
                Entry entry = api.getEntryString(arg);
                EntryService.save(entry, session);
                transaction.commit();
                System.out.println(entry.getId());
            } catch (WykopException e) {
                transaction.rollback();
            } catch (ConnectionException e) {
                FileUtils.writeStringToFile(new File("LinkConnExcLog.txt"), arg + "\n", true);
                transaction.rollback();
            } catch (ConstraintViolationException e) {
                System.out.println("Constraint Violation Exception");
                transaction.rollback();
            }
        }
        session.close();
    }

    public static List<Integer> getMissedEntries(String path) throws IOException {
        String line = null;
        List<Integer> result = new ArrayList<Integer>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            while ((line = br.readLine())!=null) {
                try {
                    Integer dupa = Integer.parseInt(line.trim());
                    result.add(dupa);
                }catch (NumberFormatException e){
//                    System.out.println("To nie liczba");
                }
            }
            return result;
        }
    }
}



