package pl.poznan.put.cs.wykop.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import pl.poznan.put.cs.wykop.dao.ReceiverDAO;
import pl.poznan.put.cs.wykop.dao.TagDAO;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by dk994 on 12.03.15.
 */
@Entity
@Table(name = "comment")
//TODO Embed, tags, receivers handling
@JsonIgnoreProperties({"author_avatar","violation_url","user_favorite","embed","user_vote","can_vote","status", "author_avatar_big", "author_avatar_med", "author_avatar_lo"})
public class LinkComment {
    @Id
    @Column(name = "id")
    private long id;
    @Column(name = "link_id")
    private long linkId;
    @Column(name = "date")
    private Date date;
    @Column(name = "author")
    private String author;
    @Column(name = "author_group")
    private long authorGroup;
    @Column(name = "author_sex")
    private String authorSex;
    @Column(name = "vote_count")
    private long voteCount;
    @Column(name = "plus")
    private long plus;
    @Column(name = "minus")
    private long minus;
    @Column(name = "body")
    private String body;
    @Column(name = "parent_id")
    private long parentId;
    @Column(name = "source")
    private String source;
    @Column(name = "blocked")
    private boolean blocked;
    @Column(name = "deleted")
    private boolean deleted;
    @Column(name = "type")
    private String type;
    @Column(name = "app")
    private String app;
    @Embedded
    private Embed embed;
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name="comment_tag",
            joinColumns={@JoinColumn(name="id_comment")},
            inverseJoinColumns={@JoinColumn(name="id_tag")})
    private Set<Tag> tags;
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "comment_receiver",
            joinColumns = {@JoinColumn(name = "id_comment")},
            inverseJoinColumns = {@JoinColumn(name = "id_receiver")})
    private Set<Receiver> receivers;
    @ManyToOne
    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
    private Link link;

    public long getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(long id) {
        this.id = id;
    }

    public long getLinkId() {
        return linkId;
    }

    public void setLinkId(long linkId) {
        this.linkId = linkId;
    }

    public Date getDate() {
        return date;
    }

    @JsonProperty("date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "CET")
    public void setDate(Date date) {
        this.date = date;
    }

    public String getAuthor() {
        return author;
    }

    @JsonProperty("author")
    public void setAuthor(String author) {
        this.author = author;
    }

    public long getAuthorGroup() {
        return authorGroup;
    }

    @JsonProperty("author_group")
    public void setAuthorGroup(long authorGroup) {
        this.authorGroup = authorGroup;
    }

    public String getAuthorSex() {
        return authorSex;
    }

    @JsonProperty("author_sex")
    public void setAuthorSex(String authorSex) {
        this.authorSex = authorSex;
    }

    public long getVoteCount() {
        return voteCount;
    }

    @JsonProperty("vote_count")
    public void setVoteCount(long voteCount) {
        this.voteCount = voteCount;
    }

    public long getPlus() {
        return plus;
    }

    @JsonProperty("vote_count_plus")
    public void setPlus(long plus) {
        this.plus = plus;
    }

    public long getMinus() {
        return minus;
    }

    @JsonProperty("vote_count_minus")
    public void setMinus(long minus) {
        this.minus = minus;
    }

    public String getBody() {
        return body;
    }

    @JsonProperty("body")
    public void setBody(String body) {
        this.body = body;
    }

    public long getParentId() {
        return parentId;
    }

    @JsonProperty("parent_id")
    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public String getSource() {
        return source;
    }

    @JsonProperty("source")
    public void setSource(String source) {
        this.source = source;
    }

    public boolean isBlocked() {
        return blocked;
    }

    @JsonProperty("blocked")
    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public boolean isDeleted() {
        return deleted;
    }

    @JsonProperty("deleted")
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    public String getApp() {
        return app;
    }

    @JsonProperty("app")
    public void setApp(String app) {
        this.app = app;
    }

    public void inflateTags(TagDAO tagDAO) {
        Pattern pattern = Pattern.compile("(?<![^\\s]+)#[a-zA-Z0-9]+");
        Matcher matcher = pattern.matcher(this.body);
        tags = new HashSet<Tag>();
        while (matcher.find()) {
            String name = matcher.group();
            Tag tag = tagDAO.getTag(name);
            tags.add(tag);
        }
    }

    public void inflateReceivers(ReceiverDAO receiverDAO) {
        Pattern pattern = Pattern.compile("(?<![^\\s]+)@[a-zA-Z0-9]+");
        Matcher matcher = pattern.matcher(this.body);
        receivers = new HashSet<Receiver>();
        while (matcher.find()) {
            String name = matcher.group();
            Receiver receiver = receiverDAO.getReceiver(name);
            receivers.add(receiver);
        }
    }

    public void hydrate(TagDAO tagDAO, ReceiverDAO receiverDAO) {
        this.inflateReceivers(receiverDAO);
    }

    @Override
    public String toString() {
        return "LinkComment{" +
                "id=" + id +
                ", linkId=" + linkId +
                ", date=" + date +
                ", author='" + author + '\'' +
                ", authorGroup=" + authorGroup +
                ", authorSex='" + authorSex + '\'' +
                ", voteCount=" + voteCount +
                ", plus=" + plus +
                ", minus=" + minus +
                ", body='" + body + '\'' +
                ", parentId=" + parentId +
                ", source='" + source + '\'' +
                ", blocked=" + blocked +
                ", deleted=" + deleted +
                ", type='" + type + '\'' +
                ", app='" + app + '\'' +
                '}';
    }
}
