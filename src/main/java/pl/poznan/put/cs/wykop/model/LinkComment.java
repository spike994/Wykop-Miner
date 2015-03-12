package pl.poznan.put.cs.wykop.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Date;

/**
 * Created by dk994 on 12.03.15.
 */
@JsonIgnoreProperties({"author_avatar","author_avatar_big","author_avatar_med","author_avatar_lo"})
public class LinkComment {
    private long id;
    private long linkId;
    private Date date;
    private String author;
    private int authorGroup;
    private String authorSex;
    private int voteCount;
    private int plus;
    private int minus;
    private String body;
    private long parentId;
    private String source;
    private boolean blocked;
    private boolean deleted;
    private String type;
    private String app;

    public long getId() {
        return id;
    }

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

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getAuthorGroup() {
        return authorGroup;
    }

    public void setAuthorGroup(int authorGroup) {
        this.authorGroup = authorGroup;
    }

    public String getAuthorSex() {
        return authorSex;
    }

    public void setAuthorSex(String authorSex) {
        this.authorSex = authorSex;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public int getPlus() {
        return plus;
    }

    public void setPlus(int plus) {
        this.plus = plus;
    }

    public int getMinus() {
        return minus;
    }

    public void setMinus(int minus) {
        this.minus = minus;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
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
