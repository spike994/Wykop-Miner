package pl.poznan.put.cs.wykop.service;

import org.hibernate.Session;
import pl.poznan.put.cs.wykop.dao.TagDAO;
import pl.poznan.put.cs.wykop.model.EntryComment;

/**
 * Created by dk994 on 16.10.14.
 */
public class EntryCommentService {
    TagDAO tDAO = new TagDAO(); //TODO zawiera odwołanie do session

    public static EntryComment save(EntryComment entryComment, Session session) {
//        entryComment.inflateTags(TagDAO);
        Long id = (Long) session.save(entryComment);
        entryComment.setId(id);
        return entryComment;
    }

}
