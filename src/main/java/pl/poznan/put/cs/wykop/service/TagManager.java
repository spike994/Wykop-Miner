package pl.poznan.put.cs.wykop.service;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import pl.poznan.put.cs.wykop.model.Tag;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by dk994 on 27.11.14.
 */
public class TagManager {
    public static List<Tag> filterTags(List<Tag> tags, Session session) {
        List<Tag> results = new ArrayList<Tag>();
        Criteria criteria = session.createCriteria(Tag.class);
        Disjunction disjunction = Restrictions.disjunction();
        for(Tag tag : tags) {
            disjunction.add(Restrictions.eq("name", tag.getName()));
        }
        criteria.add(disjunction);
        results = criteria.list();
        for(Iterator<Tag> iterator = results.iterator();iterator.hasNext();){
            Tag t = iterator.next();
            for(Iterator<Tag> iterator1 = tags.iterator(); iterator1.hasNext();){
                Tag tag = iterator1.next();
                if(t.getName().equalsIgnoreCase(tag.getName())){
                    iterator1.remove();
                }
            }
        }
        return tags;
    }
}
