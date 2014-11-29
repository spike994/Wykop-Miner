package pl.poznan.put.cs.wykop.service;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import pl.poznan.put.cs.wykop.model.Entry;
import pl.poznan.put.cs.wykop.model.Tag;
import pl.poznan.put.cs.wykop.util.HibernateUtil;
import pl.poznan.put.cs.wykop.util.TagManager;

import javax.swing.text.html.HTML;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * Created by Dawid on 2014-10-09.
 */
public class EntryService {
    public static Entry save(Entry entry) {


        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        session.beginTransaction();
        entry.setTags(filterTags(entry.inflateTags(entry.getBody())));
        Long id = (Long) session.save(entry);
        entry.setId(id);
        session.getTransaction().commit();
        session.close();
        return entry;
    }

    public static List<Tag> filterTags(List<Tag> tags) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        session.beginTransaction();
        List<Tag> results = new ArrayList<Tag>();
        List<Tag> helpfulList = new ArrayList<Tag>();
        for(Tag tag : tags) {
            Criteria criteria = session.createCriteria(Tag.class);
            criteria.add(Restrictions.eq("name", tag.getName()));
            helpfulList = criteria.list();
            for(Tag t : helpfulList){
                results.add(t);
            }
        }


        for(Iterator<Tag> iterator = results.iterator();iterator.hasNext();){
            Tag t = iterator.next();
            for(Iterator<Tag> iterator1 = tags.iterator(); iterator1.hasNext();){
                Tag tag = iterator1.next();
                if(t.getName().equalsIgnoreCase(tag.getName())){
                    iterator1.remove();
                }
            }
        }
        session.getTransaction().commit();
        session.close();

        return tags;
    }
}
