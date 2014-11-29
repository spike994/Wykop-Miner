package pl.poznan.put.cs.wykop.util;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import pl.poznan.put.cs.wykop.model.Tag;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dk994 on 27.11.14.
 */
public class TagManager {
    public static List<Tag> filterUniqueTags(Session session, List<Tag> tagsToValidate){
        Criteria criteria = session.createCriteria(Tag.class);
        for(Tag tagToValidate : tagsToValidate) {
            criteria.add(Restrictions.ne("name", tagToValidate.getName()));
        }
        List<Tag> l = criteria.list();

        return l;
    }
}
