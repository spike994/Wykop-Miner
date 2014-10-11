package pl.poznan.put.cs.wykop.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties({ "author_avatar", "author_avatar_big", "author_avatar_med", "author_avatar_lo", "author_group", "author_sex", "user_vote", "violation_url" })
public class EntryComment {
	private String app;
	private String author;
	private boolean blocked;
	private String body;
	private Date date;
	private boolean deleted;
	private Embed embed;
	private long entryId;
	private long id;
	private String source;
	private String type;
	private int voteCount;
	private List<Voter> voters;

	public String getApp() {
		return app;
	}

	public String getAuthor() {
		return author;
	}

	public String getBody() {
		return body;
	}

	public Date getDate() {
		return date;
	}

	public Embed getEmbed() {
		return embed;
	}

	public long getEntryId() {
		return entryId;
	}

	public long getId() {
		return id;
	}

	public String getSource() {
		return source;
	}

	public String getType() {
		return type;
	}

	public int getVoteCount() {
		return voteCount;
	}

	public List<Voter> getVoters() {
		return voters;
	}

	public boolean isBlocked() {
		return blocked;
	}

	public boolean isDeleted() {
		return deleted;
	}

	@JsonProperty("app")
	public void setApp(String app) {
		this.app = app;
	}

	@JsonProperty("author")
	public void setAuthor(String author) {
		this.author = author;
	}

	@JsonProperty("blocked")
	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}

	@JsonProperty("body")
	public void setBody(String body) {
		this.body = body;
	}

	@JsonProperty("date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "CET")
	public void setDate(Date date) {
		this.date = date;
	}

	@JsonProperty("deleted")
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	@JsonProperty("embed")
	public void setEmbed(Embed embed) {
		this.embed = embed;
	}

	@JsonProperty("entry_id")
	public void setEntryId(long entryId) {
		this.entryId = entryId;
	}

	@JsonProperty("id")
	public void setId(long id) {
		this.id = id;
	}

	@JsonProperty("source")
	public void setSource(String source) {
		this.source = source;
	}

	@JsonProperty("type")
	public void setType(String type) {
		this.type = type;
	}

	@JsonProperty("vote_count")
	public void setVoteCount(int voteCount) {
		this.voteCount = voteCount;
	}

	@JsonProperty("voters")
	public void setVoters(List<Voter> voters) {
		this.voters = voters;
	}

	@Override
	public String toString() {
		return "EntryComment [app=" + app + ", author=" + author + ", blocked=" + blocked + ", body=" + body + ", date=" + date + ", deleted=" + deleted + ", embed=" + embed + ", entryId=" + entryId
				+ ", id=" + id + ", source=" + source + ", type=" + type + ", voteCount=" + voteCount + ", voters=" + voters + "]";
	}

}