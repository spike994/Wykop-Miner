package pl.poznan.put.cs.wykop.service;


import org.hibernate.Session;
import pl.poznan.put.cs.wykop.dao.ReceiverDAO;
import pl.poznan.put.cs.wykop.dao.TagDAO;
import pl.poznan.put.cs.wykop.model.LinkComment;

/**
 * Created by dk994 on 12.03.15.
 */
public class LinkCommentService {

    public static LinkComment save(LinkComment linkComment, Session session){

        TagDAO tagDAO = new TagDAO();
        ReceiverDAO receiverDAO = new ReceiverDAO();
        linkComment.hydrate(tagDAO, receiverDAO);
        Long id = (Long) session.save(linkComment);
        linkComment.setId(id);
        return linkComment;
    }
}
