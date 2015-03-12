package pl.poznan.put.cs.wykop.model;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by dk994 on 21.10.14.
 */
@Entity
@Table(name = "tag")
public class Tag {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;
    @Column(name = "name")
     private String name;

    @ManyToMany(mappedBy = "tags")
    private List<Entry> entries;

    @ManyToMany(mappedBy = "tags")
    private List<EntryComment> entryComments;

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public List<Entry> getEntries() {
        return entries;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }

    public long getId() {

        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<EntryComment> getEntryComments() {
        return entryComments;
    }

    public void setEntryComments(List<EntryComment> entryComments) {
        this.entryComments = entryComments;
    }

}
