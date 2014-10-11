package pl.poznan.put.cs.wykop.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pl.poznan.put.cs.wykop.model.Entry;
import pl.poznan.put.cs.wykop.util.HibernateUtil;

/**
 * Created by Dawid on 2014-10-09.
 */
public class EntryService {
    public static Entry save(Entry employee) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();

        session.beginTransaction();

        Long id = (Long) session.save(employee);
        employee.setId(id);

        session.getTransaction().commit();

        session.close();

        return employee;
    }

}
