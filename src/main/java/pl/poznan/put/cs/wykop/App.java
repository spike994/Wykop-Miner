package pl.poznan.put.cs.wykop;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import pl.poznan.put.cs.wykop.api.Api;
import pl.poznan.put.cs.wykop.connection.ConnectionException;
import pl.poznan.put.cs.wykop.connection.WykopException;
import pl.poznan.put.cs.wykop.json.JsonException;
import pl.poznan.put.cs.wykop.model.Link;
import pl.poznan.put.cs.wykop.model.User;
import pl.poznan.put.cs.wykop.service.ConfigManager;
import pl.poznan.put.cs.wykop.service.SessionManager;

import java.io.*;
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
        Criteria criteriaMin = session
                .createCriteria(Link.class)
                .setProjection(Projections.min("id"));
        Criteria criteriaMax = session
                .createCriteria(Link.class)
                .setProjection(Projections.max("id"));
        long first = (Long) criteriaMin.uniqueResult();
        long last = (Long) criteriaMax.uniqueResult();
        ObjectMapper mapper = new ObjectMapper();

        List<String> users = getUsers("Users_uniq");


        for (String arg : users) {
            transaction = session.beginTransaction();
            try {
                String response = api.getProfileString(arg);
                User usr = mapper.readValue(response, User.class);
                session.save(usr);
                FileUtils.writeStringToFile(new File("dd"), arg + "\n", true);
                System.out.println(arg);
                transaction.commit();
            } catch (WykopException e) {
                transaction.rollback();
                FileUtils.writeStringToFile(new File("errors"), arg + "\n", true);
            }
        }

        session.close();
    }

    public static List<String> getUsers(String path) {
        String line = null;
        List<String> result = new ArrayList<String>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            while ((line = br.readLine()) != null) {
                String dupa = line.trim();
                result.add(dupa);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


}



