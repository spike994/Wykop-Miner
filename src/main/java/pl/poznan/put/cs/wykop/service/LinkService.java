package pl.poznan.put.cs.wykop.service;

import org.hibernate.Session;
import pl.poznan.put.cs.wykop.dao.ReceiverDAO;
import pl.poznan.put.cs.wykop.dao.TagDAO;
import pl.poznan.put.cs.wykop.model.Link;
import pl.poznan.put.cs.wykop.model.LinkComment;

/**
 * Created by dk994 on 12.03.15.
 */
public class LinkService {
    public static Link save(Link link, Session session){
        TagDAO tagDAO = new TagDAO();
        link.inflateTags(tagDAO);
        Long id = (Long) session.save(link);
        link.setId(id);
        return link;
    }
}
