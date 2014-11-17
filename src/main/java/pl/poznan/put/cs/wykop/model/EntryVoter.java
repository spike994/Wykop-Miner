package pl.poznan.put.cs.wykop.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@JsonIgnoreProperties({ "author_avatar", "author_avatar_big", "author_avatar_med", "author_avatar_lo", "author_group", "author_sex" })
@Entity
@Table(name = "voter")
public class EntryVoter {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;
    @Column(name = "author")
	private String author;
    @Column(name = "date")
	private Date date;
    @ManyToOne
    @JoinColumn(name = "entry_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Entry entry;
    @Column(name = "entry_id")
    private long entryId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Entry getEntry() {
        return entry;
    }

    public void setEntry(Entry entry) {
        this.entry = entry;
    }

    public EntryComment getEntryComment() {
        return entryComment;
    }

    public void setEntryComment(EntryComment entryComment) {
        this.entryComment = entryComment;
    }

    public long getEntryId() {
        return entryId;
    }

    public void setEntryId(long entryId) {
        this.entryId = entryId;
    }

    public String getAuthor() {
		return author;
	}

	public Date getDate() {
		return date;
	}

	@JsonProperty("author")
	public void setAuthor(String author) {
		this.author = author;
	}

	@JsonProperty("date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "CET")
	public void setDate(Date date) {
		this.date = date;
	}
    @Transient
    private EntryComment entryComment;



	@Override
	public String toString() {
		return "Voter [author=" + author + ", date=" + date + "]";
	}


}
