package pl.poznan.put.cs.wykop;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import pl.poznan.put.cs.wykop.model.Link;
import pl.poznan.put.cs.wykop.model.LinkComment;
import pl.poznan.put.cs.wykop.service.*;

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
        Criteria criteriaMin = session
                .createCriteria(Link.class)
                .setProjection(Projections.min("id"));
        Criteria criteriaMax = session
                .createCriteria(Link.class)
                .setProjection(Projections.max("id"));
        long first = (Long) criteriaMin.uniqueResult();
        long last = (Long) criteriaMax.uniqueResult();
        ObjectMapper mapper = new ObjectMapper();
        for (int i = (int) first; i<(int) last ; i++) {
            transaction = session.beginTransaction();

            try {
                String response = api.getLinksCommentsString(i);
                LinkComment[] comments = mapper.readValue(response, LinkComment[].class);
                for(LinkComment linkComment : comments){
                    linkComment.setLinkId(i);
                    LinkCommentService.save(linkComment,session);
                    System.out.println(linkComment.getId()+"  "+linkComment.getDate());
                }
                transaction.commit();
                FileUtils.writeStringToFile(new File("saved.txt"), i+"\n",true);

            } catch (WykopException e) {
                transaction.rollback();
            } catch (ConnectionException e) {
                FileUtils.writeStringToFile(new File("LinkConnExcLog.txt"), i + "\n", true);
                transaction.rollback();
            } catch (ConstraintViolationException e) {
                System.out.println("Constraint Violation Exception");
                transaction.rollback();
            }
        }
        session.close();
    }


}



