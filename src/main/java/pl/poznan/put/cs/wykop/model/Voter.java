package pl.poznan.put.cs.wykop.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@JsonIgnoreProperties({ "author_avatar", "author_avatar_big", "author_avatar_med", "author_avatar_lo", "author_group", "author_sex" })
@Entity
@Table(name = "voter")
public class Voter {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;
    @Column(name = "author")
	private String author;
    @Column(name = "date")
	private Date date;
    @ManyToMany(mappedBy = "voters")
    private List<Entry> entries;

    @ManyToMany(mappedBy = "voters")
    private List<EntryComment> entryComments;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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


}
