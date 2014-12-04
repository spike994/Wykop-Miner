package pl.poznan.put.cs.wykop.service;

import org.hibernate.Session;
import pl.poznan.put.cs.wykop.dao.TagDAO;
import pl.poznan.put.cs.wykop.model.Entry;

/**
 * Created by Dawid on 2014-10-09.
 */
public class EntryService {
    public static Entry save(Entry entry, Session session) {
        TagDAO tagDAO = new TagDAO();
        entry.inflateTags(tagDAO);
//        entry.setReceivers(ReceiverManager.filterTags(entry.inflateReceivers(entry.getBody()), session));
//        for(EntryComment entryComment : entry.getComments()) {
//            EntryCommentService.save(entryComment, session);
//        }
        Long id = (Long) session.save(entry);
        entry.setId(id);
        return entry;
    }
}
