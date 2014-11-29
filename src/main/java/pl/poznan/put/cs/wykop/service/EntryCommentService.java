package pl.poznan.put.cs.wykop.service;

import org.hibernate.Session;
import pl.poznan.put.cs.wykop.model.EntryComment;

/**
 * Created by dk994 on 16.10.14.
 */
public class EntryCommentService {
    public static EntryComment save(EntryComment entryComment, Session session) {

        entryComment.setTags(TagManager.filterTags(entryComment.inflateTags(entryComment.getBody()), session));
        entryComment.setReceivers(ReceiverManager.filterTags(entryComment.inflateReceivers(entryComment.getBody()), session));
        Long id = (Long) session.save(entryComment);
        entryComment.setId(id);


        return entryComment;
    }

}
