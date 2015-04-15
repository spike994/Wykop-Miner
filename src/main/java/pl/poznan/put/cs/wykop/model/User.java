package pl.poznan.put.cs.wykop.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by dk994 on 08.11.14.
 */
@Entity
@Table(name = "users")
@JsonIgnoreProperties({"email", "avatar", "avatar_big", "avatar_med", "avatar_lo", "is_observed", "is_blocked", "violation_url",
        "public_email", "name", "www", "jabber", "gg", "city", "about",})
public class User {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String login;
    @Column(name = "rank")
    private long rank;
    @Column(name = "followers")
    private long followers;
    @Column(name = "following")
    private long following;
    @Column(name = "entires")
    private long entires;
    @Column(name = "comments")
    private long comments;
    @Column(name = "entries_comments")
    private long entriesComments;
    @Column(name = "digs")
    private long digs;
    //TODO check format of buries
    @Column(name = "buries")
    private long buries;
    @Column(name = "published_links")
    private long publishedLinks;
    @Column(name = "groups")
    private long groups;
    @Column(name = "related_links")
    private long relatedLinks;
    @Column(name = "links_added")
    private long linksAdded;
    @Column(name = "entry_count")
    private long entryCount;
    @Column(name = "sex")
    private String sex;
    @Column(name = "url")
    private String url;
    @Column(name = "signup_date")
    private Date signUpDate;
    @Column(name = "user_group")
    private long userGroup;
//    @ManyToMany(mappedBy = "receivers")
//    private List<Entry> entries;
//    @ManyToMany(mappedBy = "receivers")
//    private List<EntryComment> entryComments;

    public long getRelatedLinks() {
        return relatedLinks;
    }

    @JsonProperty("related_links")
    public void setRelatedLinks(long relatedLinks) {
        this.relatedLinks = relatedLinks;
    }

    public long getLinksAdded() {
        return linksAdded;
    }

    @JsonProperty("links_added")
    public void setLinksAdded(long linksAdded) {
        this.linksAdded = linksAdded;
    }

    public long getEntryCount() {
        return entryCount;
    }

    @JsonProperty("entries")
    public void setEntryCount(long entryCount) {
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "CET")
    public void setSignUpDate(Date signUpDate) {
        this.signUpDate = signUpDate;
    }

    public long getUserGroup() {
        return userGroup;
    }

    @JsonProperty("author_group")
    public void setUserGroup(long userGroup) {
        this.userGroup = userGroup;
    }

//    public List<Entry> getEntries() {
//        return entries;
//    }
//
//    public void setEntries(List<Entry> entries) {
//        this.entries = entries;
//    }

//    public List<EntryComment> getEntryComments() {
//        return entryComments;
//    }
//
//    public void setEntryComments(List<EntryComment> entryComments) {
//        this.entryComments = entryComments;
//    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    @JsonProperty("login")
    public void setLogin(String login) {
        this.login = login;
    }

    public long getRank() {
        return rank;
    }

    @JsonProperty("rank")
    public void setRank(long rank) {
        this.rank = rank;
    }

    public long getFollowers() {
        return followers;
    }

    @JsonProperty("followers")
    public void setFollowers(long followers) {
        this.followers = followers;
    }

    public long getFollowing() {
        return following;
    }

    @JsonProperty("following")
    public void setFollowing(long following) {
        this.following = following;
    }

//    public long getEntires() {
//        return entires;
//    }
//
//    @JsonProperty("entries")
//    public void setEntires(long entires) {
//        this.entires = entires;
//    }

    public long getComments() {
        return comments;
    }

    @JsonProperty("comments")
    public void setComments(long comments) {
        this.comments = comments;
    }

    public long getEntriesComments() {
        return entriesComments;
    }

    @JsonProperty("entries_comments")
    public void setEntriesComments(long entriesComments) {
        this.entriesComments = entriesComments;
    }

    public long getDigs() {
        return digs;
    }

    @JsonProperty("diggs")
    public void setDigs(long digs) {
        this.digs = digs;
    }

    public long getBuries() {
        return buries;
    }

    @JsonProperty("buries")
    public void setBuries(long buries) {
        this.buries = buries;
    }

    public long getPublishedLinks() {
        return publishedLinks;
    }

    @JsonProperty("links_published")
    public void setPublishedLinks(long publishedLinks) {
        this.publishedLinks = publishedLinks;
    }

    public long getGroups() {
        return groups;
    }

    @JsonProperty("groups")
    public void setGroups(long groups) {
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
                '}';
    }

}
