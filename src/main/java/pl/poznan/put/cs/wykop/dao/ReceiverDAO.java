package pl.poznan.put.cs.wykop.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import pl.poznan.put.cs.wykop.model.Receiver;
import pl.poznan.put.cs.wykop.model.Tag;
import pl.poznan.put.cs.wykop.service.SessionManager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by dk994 on 29.11.14.
 */
public class ReceiverDAO {
    private Session session = SessionManager.getSessionManager().getSession();

    public Receiver getReceiver(String name){
        Criteria criteria = session.createCriteria(Receiver.class);
        criteria.add(Restrictions.eq("name", name));
        Receiver receiver = (Receiver)criteria.uniqueResult();
        if(receiver == null){
            receiver = new Receiver();
            receiver.setName(name);
            session.save(receiver);
        }
        return receiver;
    }
}
