package pl.poznan.put.cs.wykop.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties({ "author_avatar", "author_avatar_big", "author_avatar_med", "author_avatar_lo", "author_group", "author_sex", "receiver_avatar", "receiver_avatar_big", "receiver_avatar_med",
		"receiver_avatar_lo", "receiver_group", "receiver_sex", "user_vote", "user_favorite", "violation_url", "can_comment" })
public class Entry {
	private String app;
	private String author;
	private boolean blocked;
	private String body;
	private int commentCount;
	private List<EntryComment> comments;
	private Date date;
	private boolean deleted;
	private Embed embed;

	public Embed getEmbed() {
		return embed;
	}

	@JsonProperty("embed")
	public void setEmbed(Embed embed) {
		this.embed = embed;
	}

	private long id;
	private String receiver;
	private String source;
	private String type;
	private String url;
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

	public int getCommentCount() {
		return commentCount;
	}

	public List<EntryComment> getComments() {
		return comments;
	}

	public Date getDate() {
		return date;
	}

	public long getId() {
		return id;
	}

	public String getReceiver() {
		return receiver;
	}

	public String getSource() {
		return source;
	}

	public String getType() {
		return type;
	}

	public String getUrl() {
		return url;
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

	@JsonProperty("comment_count")
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	@JsonProperty("comments")
	public void setComments(List<EntryComment> comments) {
		this.comments = comments;
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

	@JsonProperty("id")
	public void setId(long id) {
		this.id = id;
	}

	@JsonProperty("receiver")
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	@JsonProperty("source")
	public void setSource(String source) {
		this.source = source;
	}

	@JsonProperty("type")
	public void setType(String type) {
		this.type = type;
	}

	@JsonProperty("url")
	public void setUrl(String url) {
		this.url = url;
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
		return "Entry [app=" + app + ", author=" + author + ", blocked=" + blocked + ", body=" + body + ", commentCount=" + commentCount + ", comments=" + comments + ", date=" + date + ", deleted="
				+ deleted + ", embed=" + embed + ", id=" + id + ", receiver=" + receiver + ", source=" + source + ", type=" + type + ", url=" + url + ", voteCount=" + voteCount + ", voters=" + voters
				+ "]";
	}
}
