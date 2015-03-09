package pl.poznan.put.cs.wykop.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;
import java.util.List;

/**
 * Created by dk994 on 23.02.15.
 */
@JsonIgnoreProperties({"tags","source_url","vote_count","comment_count","report_count","related_count","author_group",
"author_avatar", "author_avatar_big","author_avatar_med","author_avatar_lo","author_sex","type",
"group","preview","user_vote","user_favorite","user_observe","user_lists","plus18","status","can_vote","has_own_content",
        "is_hot", "category_name", "violation_url", "info","app", "own_content",
})
public class Link {
    private long id;
    private String title;
    private String description;
    private List<Tag> tags;
    private String url;
    private Date date;
    private String source;
    private String author;
    private int voteCount;
    private int commentCount;
    private boolean adult;
    private String category;
    private int reportCount;

    public long getId() {
        return id;
    }
    @JsonProperty("id")
    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
    public Date getDate() {
        return date;
    }
    @JsonProperty("date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "CET")
    public void setDate(Date date) {
        this.date = date;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getReportCount() {
        return reportCount;
    }

    public void setReportCount(int reportCount) {
        this.reportCount = reportCount;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    public String toString(){
        return "ID:"+getId()+" title:"+ getTitle();
    }
}
