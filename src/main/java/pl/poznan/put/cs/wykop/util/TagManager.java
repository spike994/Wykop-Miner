package pl.poznan.put.cs.wykop.util;

import org.hibernate.Criteria;
import org.hibernate.Session;
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
        return tags;
    }
}
