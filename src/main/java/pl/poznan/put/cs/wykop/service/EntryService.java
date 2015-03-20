package pl.poznan.put.cs.wykop.service;

import org.hibernate.Session;
import pl.poznan.put.cs.wykop.dao.ReceiverDAO;
import pl.poznan.put.cs.wykop.dao.TagDAO;
import pl.poznan.put.cs.wykop.model.Entry;
import pl.poznan.put.cs.wykop.model.EntryComment;

/**
 * Created by Dawid on 2014-10-09.
 */
public class EntryService {
        public static Entry save(Entry entry, Session session) {
            TagDAO tagDAO = new TagDAO();
            ReceiverDAO receiverDAO = new ReceiverDAO();
            entry.hydrate(tagDAO, receiverDAO);
            for(EntryComment entryComment : entry.getComments()) {
                EntryCommentService.save(entryComment, session);
            }
            Long id = (Long) session.save(entry);
            entry.setId(id);
            return entry;
    }
}
