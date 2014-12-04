package pl.poznan.put.cs.wykop.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pl.poznan.put.cs.wykop.util.HibernateUtil;

/**
 * Created by dk994 on 04.12.14.
 */
public class SessionManager {
    //TODO singleton

    private static SessionManager sessionManager = null;
    private SessionFactory sessionFactory;
    private Session session;
    protected SessionManager(){
        sessionFactory = HibernateUtil.getSessionFactory();
        session = sessionFactory.openSession();
    }

    public static SessionManager getSessionManager(){
        if(sessionManager == null){
            sessionManager = new SessionManager();
        }
        return sessionManager;
    }

    public Session getSession(){return session;}

}
