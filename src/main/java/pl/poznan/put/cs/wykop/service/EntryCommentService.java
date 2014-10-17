package pl.poznan.put.cs.wykop.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pl.poznan.put.cs.wykop.model.EntryComment;
import pl.poznan.put.cs.wykop.util.HibernateUtil;

/**
 * Created by dk994 on 16.10.14.
 */
public class EntryCommentService {
    public static EntryComment save(EntryComment entryComment) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();

        session.beginTransaction();

        Long id = (Long) session.save(entryComment);
        entryComment.setId(id);

        session.getTransaction().commit();

        session.close();

        return entryComment;
    }

}
