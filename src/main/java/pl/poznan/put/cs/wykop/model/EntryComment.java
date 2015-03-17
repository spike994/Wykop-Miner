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
@Table(name = "entry_comment")
@JsonIgnoreProperties({"author_avatar", "author_avatar_big", "author_avatar_med", "author_avatar_lo", "author_group", "author_sex", "user_vote", "violation_url"})
public class EntryComment {
    @Id
    @Column(name = "id")
    private long id;
    @Column(name = "vote_count")
    private long voteCount;
    @ManyToOne
    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
    private Entry entry;
    @Column(name = "entry_id")
    private long entryId;
    @Column(name = "app")
    private String app;
    @Column(name = "author")
    private String author;
    @Column(name = "blocked")
    private boolean blocked;
    @Column(name = "content")
    private String body;
    @Column(name = "date")
    private Date date;
    @Column(name = "deleted")
    private boolean deleted;
    @Embedded
    private Embed embed;
    @Column(name = "receiver")
    private String receiver;
    @Transient
    @Column(name = "source")
    private String source;
    @Column(name = "type")
    private String type;
    @Column(name = "url")
    private String url;
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "entry_comment_receiver",
            joinColumns = {@JoinColumn(name = "entry_comment_id")},
            inverseJoinColumns = {@JoinColumn(name = "receiver_id")})
    private Set<Receiver> receivers;
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "entry_comment_voter",
            joinColumns = {@JoinColumn(name = "entry_comment_id")},
            inverseJoinColumns = {@JoinColumn(name = "voter_id")})
    private List<Voter> voters;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "entry_comment_tag",
            joinColumns = {@JoinColumn(name = "entry_comment_id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id")})
    private Set<Tag> tags;

    public String getApp() {
        return app;
    }

    @JsonProperty("app")
    public void setApp(String app) {
        this.app = app;
    }

    public void setVoteCount(long voteCount) {
        this.voteCount = voteCount;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
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

    public Date getDate() {
        return date;
    }

    @JsonProperty("date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "CET")
    public void setDate(Date date) {
        this.date = date;
    }

    public Embed getEmbed() {
        return embed;
    }

    @JsonProperty("embed")
    public void setEmbed(Embed embed) {
        this.embed = embed;
    }

    public long getEntryId() {
        return entryId;
    }

    @JsonProperty("entry_id")
    public void setEntryId(long entryId) {
        this.entryId = entryId;
    }

    public long getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(long id) {
        this.id = id;
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

    public Entry getEntry() {
        return entry;
    }

    public void setEntry(Entry entry) {
        this.entry = entry;
    }

    public boolean isDeleted() {
        return deleted;
    }

    @JsonProperty("deleted")
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Set<Receiver> getReceivers() {
        return receivers;
    }

    public void setReceivers(Set<Receiver> receivers) {
        this.receivers = receivers;
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
        this.inflateTags(tagDAO);
        this.inflateReceivers(receiverDAO);
    }

    @Override
    public String toString() {
        return "EntryComment{" +
                "\nid=" + id +
                ",\n voteCount=" + voteCount +
                ",\n entry=" + entry +
                ",\n entryId=" + entryId +
                ",\n app='" + app + '\'' +
                ",\n author='" + author + '\'' +
                ",\n blocked=" + blocked +
                ",\n body='" + body + '\'' +
                ",\n date=" + date +
                ",\n deleted=" + deleted +
                ",\n embed=" + embed +
                ",\n receiver='" + receiver + '\'' +
                ",\n source='" + source + '\'' +
                ",\n type='" + type + '\'' +
                ",\n url='" + url + '\'' +
                ",\n receivers=" + receivers +
                ",\n voters=" + voters +
                ",\n tags=" + tags +
                '}';
    }

}
