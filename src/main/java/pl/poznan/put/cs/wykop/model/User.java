package pl.poznan.put.cs.wykop.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

/**
 * Created by dk994 on 08.11.14.
 */
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    @Column(name = "login")
    private String login;
    @Column(name = "rank")
    private int rank;
    @Column(name = "followers")
    private int followers;
    @Column(name = "following")
    private int following;
    @Column(name = "entires")
    private int entires;
    @Column(name = "comments")
    private int comments;
    @Column(name = "entriesComments")
    private int entriesComments;
    @Column(name = "digs")
    private int digs;
    //TODO check format of buries
    @Column(name = "buries")
    private int buries;
    @Column(name = "publishedLinks")
    private int publishedLinks;
    @Column(name = "groups")
    private int groups;
    private int relatedLinks;
    private int linksAdded;
    private int entryCount;
    private String sex;
    private String url;
    private Date signUpDate;
    private int userGroup;
    @ManyToMany(mappedBy = "receivers")
    private List<Entry> entries;
    @ManyToMany(mappedBy = "receivers")
    private List<EntryComment> entryComments;

    public int getRelatedLinks() {
        return relatedLinks;
    }

    @JsonProperty("related_links")
    public void setRelatedLinks(int relatedLinks) {
        this.relatedLinks = relatedLinks;
    }

    public int getLinksAdded() {
        return linksAdded;
    }

    @JsonProperty("links_added")
    public void setLinksAdded(int linksAdded) {
        this.linksAdded = linksAdded;
    }

    public int getEntryCount() {
        return entryCount;
    }

    @JsonProperty("entries")
    public void setEntryCount(int entryCount) {
        this.entryCount = entryCount;
    }

    public String getSex() {
        return sex;
    }

    @JsonProperty("sex")
    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    public Date getSignUpDate() {
        return signUpDate;
    }

    @JsonProperty("signup_date")
    public void setSignUpDate(Date signUpDate) {
        this.signUpDate = signUpDate;
    }

    public int getUserGroup() {
        return userGroup;
    }

    @JsonProperty("author_group")
    public void setUserGroup(int userGroup) {
        this.userGroup = userGroup;
    }

    public List<Entry> getEntries() {
        return entries;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }

    public List<EntryComment> getEntryComments() {
        return entryComments;
    }

    public void setEntryComments(List<EntryComment> entryComments) {
        this.entryComments = entryComments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    @JsonProperty("login")
    public void setLogin(String login) {
        this.login = login;
    }

    public int getRank() {
        return rank;
    }

    @JsonProperty("rank")
    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getFollowers() {
        return followers;
    }

    @JsonProperty("followers")
    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public int getFollowing() {
        return following;
    }

    @JsonProperty("following")
    public void setFollowing(int following) {
        this.following = following;
    }

    public int getEntires() {
        return entires;
    }

    @JsonProperty("entries")
    public void setEntires(int entires) {
        this.entires = entires;
    }

    public int getComments() {
        return comments;
    }

    @JsonProperty("comments")
    public void setComments(int comments) {
        this.comments = comments;
    }

    public int getEntriesComments() {
        return entriesComments;
    }

    @JsonProperty("entries_comments")
    public void setEntriesComments(int entriesComments) {
        this.entriesComments = entriesComments;
    }

    public int getDigs() {
        return digs;
    }

    @JsonProperty("diggs")
    public void setDigs(int digs) {
        this.digs = digs;
    }

    public int getBuries() {
        return buries;
    }

    @JsonProperty("buries")
    public void setBuries(int buries) {
        this.buries = buries;
    }

    public int getPublishedLinks() {
        return publishedLinks;
    }

    @JsonProperty("publishedLinks")
    public void setPublishedLinks(int publishedLinks) {
        this.publishedLinks = publishedLinks;
    }

    public int getGroups() {
        return groups;
    }

    @JsonProperty("groups")
    public void setGroups(int groups) {
        this.groups = groups;
    }

    @Override
    public String toString() {
        return "User{" +
                "\nid=" + id +
                ",\n login='" + login + '\'' +
                ",\n rank=" + rank +
                ",\n followers=" + followers +
                ",\n following=" + following +
                ",\n entires=" + entires +
                ",\n comments=" + comments +
                ",\n entriesComments=" + entriesComments +
                ",\n digs=" + digs +
                ",\n buries=" + buries +
                ",\n publishedLinks=" + publishedLinks +
                ",\n groups=" + groups +
                ",\n relatedLinks=" + relatedLinks +
                ",\n linksAdded=" + linksAdded +
                ",\n entryCount=" + entryCount +
                ",\n sex='" + sex + '\'' +
                ",\n url='" + url + '\'' +
                ",\n signUpDate=" + signUpDate +
                ",\n userGroup=" + userGroup +
                ",\n entries=" + entries +
                ",\n entryComments=" + entryComments +
                '}';
    }

}
