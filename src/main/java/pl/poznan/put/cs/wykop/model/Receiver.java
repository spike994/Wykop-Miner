package pl.poznan.put.cs.wykop.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by dk994 on 10.11.14.
 */
@Entity
@Table(name = "receiver")
public class Receiver {
        @Id
        @GeneratedValue
        @Column(name = "id")
        private int id;
        @Column(name = "name")
        private String name;
        @ManyToMany(mappedBy = "tags")
        private List<Entry> entries;

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

    @ManyToMany(mappedBy = "tags")
        private List<EntryComment> entryComments;
}
