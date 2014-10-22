package pl.poznan.put.cs.wykop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Created by dk994 on 21.10.14.
 */
@Entity
@Table(name = "tag")
public class Tag {
    Entry entry;
    @Column(name = "name")
    private String name;
    @Column(name = "entry_id")
    private long entryId;
    @Transient
    @Column(name = "link_id")
    private long linkId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getEntryId() {
        return entryId;
    }

    public void setEntryId(long entryId) {
        this.entryId = entryId;
    }

    public long getLinkId() {
        return linkId;
    }

    public void setLinkId(long linkId) {
        this.linkId = linkId;
    }
}
