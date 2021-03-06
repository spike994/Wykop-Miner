package pl.poznan.put.cs.wykop.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import pl.poznan.put.cs.wykop.dao.ReceiverDAO;
import pl.poznan.put.cs.wykop.dao.TagDAO;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
@Table(name = "entry")
@JsonIgnoreProperties({"author_avatar", "author_avatar_big", "author_avatar_med", "author_avatar_lo", "author_group", "author_sex", "receiver_avatar", "receiver_avatar_big", "receiver_avatar_med",
        "receiver_avatar_lo", "receiver_group", "receiver_sex", "user_vote", "user_favorite", "violation_url", "can_comment"})
public class Entry {
    @Column(name = "app")
    private String app;
    @Column(name = "author")
    private String author;
    @Column(name = "blocked")
    private boolean blocked;
    @Column(name = "content")
    private String body;
    @Column(name = "comment_count")
    private long commentCount;
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name="entry_voter",
            joinColumns={@JoinColumn(name="entry_id")},
            inverseJoinColumns={@JoinColumn(name="voter_id")})
    private List<Voter> voters;
    @OneToMany(mappedBy = "entry", cascade = CascadeType.ALL)
    private List<EntryComment> comments;
    @Column(name = "date")
    private Date date;
    @Column(name = "deleted")
    private boolean deleted;
    @Embedded
    private Embed embed;
    @Id
    @Column(name = "id")
    private long id;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
    @Transient
    @Column(name = "source")
    private String source;
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "entry_receiver",
        joinColumns = {@JoinColumn(name = "entry_id")},
            inverseJoinColumns = {@JoinColumn(name = "receiver_id")})
    private Set<Receiver> receivers;
    @Column(name = "type")
    private String type;
    @Column(name= "author_group")
    private long authorGroup;
    @Column(name = "url")
    private String url;
    @Column(name = "votes")
    private long voteCount;
    @Column(name = "receiver")
    private String receiver;
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name="entry_tag",
            joinColumns={@JoinColumn(name="entry_id")},
            inverseJoinColumns={@JoinColumn(name="tag_id")})
    private Set<Tag> tags;


    public String getApp() {
        return app;
    }

    @JsonProperty("app")
    public void setApp(String app) {
        this.app = app;
    }
    @JsonProperty("embed")
    public void setEmbed(Embed embed) {
        this.embed = embed;
    }

    public String getAuthor() {
        return author;
    }

    @JsonProperty("author")
    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBody() {
        return body;
    }

    @JsonProperty("body")
    public void setBody(String body) {
        this.body = body;
    }

    public long getCommentCount() {
        return commentCount;
    }

    @JsonProperty("comment_count")
    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public List<EntryComment> getComments() {
        return comments;
    }

    @JsonProperty("comments")
    public void setComments(List<EntryComment> comments) {
        this.comments = comments;
    }

    public Date getDate() {
        return date;
    }

    @JsonProperty("date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "CET")
    public void setDate(Date date) {
        this.date = date;
    }

    public long getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(long id) {
        this.id = id;
    }

    public String getReceiver() {
        return receiver;
    }

    @JsonProperty("receiver")
    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getSource() {
        return source;
    }

    @JsonProperty("source")
    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    public long getVoteCount() {
        return voteCount;
    }

    @JsonProperty("vote_count")
    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public List<Voter> getVoters() {
        return voters;
    }

    @JsonProperty("voters")
    public void setVoters(List<Voter> voters) {
        this.voters = voters;
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

    public long getAuthorGroup() {
        return authorGroup;
    }

    public void setAuthorGroup(long authorGroup) {
        this.authorGroup = authorGroup;
    }


    public Set<Receiver> getReceivers() {
        return receivers;
    }

    public void setReceivers(Set<Receiver> receivers) {
        this.receivers = receivers;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public void hydrate(TagDAO tagDAO, ReceiverDAO receiverDAO){
        inflateReceivers(receiverDAO);
        inflateTags(tagDAO);
    }

    public void inflateTags(TagDAO tagDAO){
        Pattern pattern = Pattern.compile("(?<![^\\s]+)#[a-zA-Z0-9]+");
        Matcher matcher = pattern.matcher(this.body);
        tags = new HashSet<Tag>();
        while(matcher.find())
        {
            String name = matcher.group();
            Tag tag = tagDAO.getTag(name);
            tags.add(tag);
            tag.setName(matcher.group());
        }
    }
    public void inflateReceivers(ReceiverDAO receiverDAO){
        Pattern pattern = Pattern.compile("(?<![^\\s]+)@[a-zA-Z0-9]+");
        Matcher matcher = pattern.matcher(this.body);
        receivers = new HashSet<Receiver>();
        while(matcher.find())
        {
            String name = matcher.group();
            Receiver rec = receiverDAO.getReceiver(name);
            receivers.add(rec);
            rec.setName(matcher.group());
        }
    }

    @Override
    public String toString() {
        return "Entry{" +
                "\napp='" + app + '\'' +
                ",\n author='" + author + '\'' +
                ",\n blocked=" + blocked +
                ",\n body='" + body + '\'' +
                ",\n commentCount=" + commentCount +
                ",\n voters=" + voters +
                ",\n comments=" + comments +
                ",\n date=" + date +
                ",\n deleted=" + deleted +
                ",\n embed=" + embed +
                ",\n id=" + id +
                ",\n source='" + source + '\'' +
                ",\n receivers=" + receivers +
                ",\n type='" + type + '\'' +
                ",\n authorGroup=" + authorGroup +
                ",\n url='" + url + '\'' +
                ",\n voteCount=" + voteCount +
                ",\n receiver='" + receiver + '\'' +
                ",\n tags=" + tags +
                '}';
    }
}
