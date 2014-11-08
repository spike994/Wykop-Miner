package pl.poznan.put.cs.wykop.model;

import javax.persistence.*;

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
    @Column(name= "name")
    private String name;
    @Column(name = "rank")
    private int rank;
    @Column(name ="followers")
    private int followers;
    @Column(name ="following")
    private int following;
    @Column(name ="entires")
    private int entires;
    @Column(name ="comments")
    private int comments;
    @Column(name ="entriesComments")
    private int entriesComments;
    @Column(name ="digs")
    private int digs;
    @Column(name ="buries")
    private int buries;
    @Column(name ="evaluatedLinks")
    private int evaluatedLinks;
    @Column(name ="groups")
    private int groups;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public int getFollowing() {
        return following;
    }

    public void setFollowing(int following) {
        this.following = following;
    }

    public int getEntires() {
        return entires;
    }

    public void setEntires(int entires) {
        this.entires = entires;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public int getEntriesComments() {
        return entriesComments;
    }

    public void setEntriesComments(int entriesComments) {
        this.entriesComments = entriesComments;
    }

    public int getDigs() {
        return digs;
    }

    public void setDigs(int digs) {
        this.digs = digs;
    }

    public int getBuries() {
        return buries;
    }

    public void setBuries(int buries) {
        this.buries = buries;
    }

    public int getEvaluatedLinks() {
        return evaluatedLinks;
    }

    public void setEvaluatedLinks(int evaluatedLinks) {
        this.evaluatedLinks = evaluatedLinks;
    }

    public int getGroups() {
        return groups;
    }

    public void setGroups(int groups) {
        this.groups = groups;
    }
}
