package pl.poznan.put.cs.wykop.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "entry")
@JsonIgnoreProperties({"author_avatar", "author_avatar_big", "author_avatar_med", "author_avatar_lo", "author_group", "author_sex", "receiver_avatar", "receiver_avatar_big", "receiver_avatar_med",
        "receiver_avatar_lo", "receiver_group", "receiver_sex", "user_vote", "user_favorite", "violation_url", "can_comment"})
public class Entry {
    @Column(name = "app")
    private String app;
    @Column(name = "author")
    private String author;
    @Transient
    @Column(name = "blocked")
    private boolean blocked;
    @Column(name = "content")
    private String body;
    @Column(name = "comment_count")
    private long commentCount;
    @OneToMany(mappedBy = "entry", cascade = CascadeType.ALL)
    private List<EntryComment> comments;
    @Column(name = "date")
    private Date date;
    @Transient
    @Column(name = "deleted")
    private boolean deleted;
//    @Column(name = "embed")
    @Transient
    private Embed embed;
    @Id
    @Column(name = "id")
    private long id;
    @Column(name = "receiver")
    private String receiver;
    @Column(name = "source")
    private String source;
    @Column(name = "type")
    private String type;
    @Column(name = "url")
    private String url;
    @Column(name = "votes")
    private long voteCount;
    @Transient
    private List<Voter> voters;
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name="entry_tag",
            joinColumns={@JoinColumn(name="entry_id")},
            inverseJoinColumns={@JoinColumn(name="tag_id")})
    private List<Tag> tags;


    public String getApp() {
        return app;
    }

    @JsonProperty("app")
    public void setApp(String app) {
        this.app = app;
    }

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



    @Override
    public String toString() {
        return "Entry [app=" + app + ", author=" + author + ", blocked=" + blocked + ", body=" + body + ", commentCount=" + commentCount + ", comments=" + comments + ", date=" + date + ", deleted="
                + deleted + ", embed=" + embed + ", id=" + id + ", receiver=" + receiver + ", source=" + source + ", type=" + type + ", url=" + url + ", voteCount=" + voteCount + ", voters=" + voters
                + "]";
    }
}
