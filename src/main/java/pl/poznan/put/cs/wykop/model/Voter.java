package pl.poznan.put.cs.wykop.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties({ "author_avatar", "author_avatar_big", "author_avatar_med", "author_avatar_lo", "author_group", "author_sex" })
public class Voter {
	private String author;
	private Date date;

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

    private Entry entry;
    private EntryComment entryComment;

	@Override
	public String toString() {
		return "Voter [author=" + author + ", date=" + date + "]";
	}
}
