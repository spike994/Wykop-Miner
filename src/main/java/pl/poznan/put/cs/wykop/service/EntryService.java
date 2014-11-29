package pl.poznan.put.cs.wykop.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pl.poznan.put.cs.wykop.model.Entry;
import pl.poznan.put.cs.wykop.model.EntryComment;
import pl.poznan.put.cs.wykop.util.HibernateUtil;
import pl.poznan.put.cs.wykop.util.ReceiverManager;
import pl.poznan.put.cs.wykop.util.TagManager;

/**
 * Created by Dawid on 2014-10-09.
 */
public class EntryService {
    public static Entry save(Entry entry) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        session.beginTransaction();
        entry.setTags(TagManager.filterTags(entry.inflateTags(entry.getBody()), session));
        entry.setReceivers(ReceiverManager.filterTags(entry.inflateReceivers(entry.getBody()), session));
        for(EntryComment entryComment : entry.getComments()) {
            EntryCommentService.save(entryComment, session);
        }
        Long id = (Long) session.save(entry);
        entry.setId(id);
        session.getTransaction().commit();
        session.close();
        return entry;
    }
}
