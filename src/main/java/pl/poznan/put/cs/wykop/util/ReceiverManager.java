package pl.poznan.put.cs.wykop.util;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import pl.poznan.put.cs.wykop.model.Receiver;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by dk994 on 29.11.14.
 */
public class ReceiverManager {
    public static List<Receiver> filterTags(List<Receiver> receivers, Session session) {
        List<Receiver> results = new ArrayList<Receiver>();
        List<Receiver> helpfulList = new ArrayList<Receiver>();
        for(Receiver receiver1 : receivers) {
            Criteria criteria = session.createCriteria(Receiver.class);
            criteria.add(Restrictions.eq("name", receiver1.getName()));
            helpfulList = criteria.list();
            for(Receiver t : helpfulList){
                results.add(t);
            }
        }
        for(Iterator<Receiver> iterator = results.iterator();iterator.hasNext();){
            Receiver r = iterator.next();
            for(Iterator<Receiver> iterator1 = receivers.iterator(); iterator1.hasNext();){
                Receiver receiver = iterator1.next();
                if(r.getName().equalsIgnoreCase(receiver.getName())){
                    iterator1.remove();
                }
            }
        }
        return receivers;
    }
}
