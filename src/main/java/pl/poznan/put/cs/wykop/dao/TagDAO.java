package pl.poznan.put.cs.wykop.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import pl.poznan.put.cs.wykop.model.Tag;
import pl.poznan.put.cs.wykop.service.SessionManager;

import java.util.*;

/**
 * Created by dk994 on 27.11.14.
 */
public class TagDAO {
    private Session session = SessionManager.getSessionManager().getSession();

    public Tag getTag(String name){
        Criteria criteria = session.createCriteria(Tag.class);
        criteria.add(Restrictions.eq("name", name));
        Tag tag = (Tag)criteria.uniqueResult();
        if(tag == null){
            tag = new Tag();
            tag.setName(name);
            session.save(tag);
        }
        return tag;

    }
}
