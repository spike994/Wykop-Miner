package pl.poznan.put.cs.wykop.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;

/**
 * Created by dk994 on 23.02.15.
 */
@JsonIgnoreProperties({"author_avatar", "author_avatar_big", "author_avatar_med", "author_avatar_lo", "user_vote", "user_favorite", "user_observe", "user_lists", "status", "can_vote",
        "is_hot", "violation_url", "info", "own_content",
})
public class Link {
    private long id;
    private String title;
    private String description;
    private String url;
    private Date date;
    private String source;
    private String author;
    private int voteCount;
    private int commentCount;
    private int relatedCount;
    private String authorGroup;
    private boolean adult;
    private String tags;
    private String category;
    private int reportCount;
    private String authorSex;
    private String type;
    private String group;
    private String preview;
    private boolean hasOwnContent;
    private String categoryName;
    private String app;

    public String getCategoryName() {
        return categoryName;
    }
    @JsonProperty("category_name")
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public boolean isHasOwnContent() {
        return hasOwnContent;
    }
    @JsonProperty("has_own_content")
    public void setHasOwnContent(boolean hasOwnContent) {
        this.hasOwnContent = hasOwnContent;
    }

    public String getApp() {
        return app;
    }
    @JsonProperty("app")
    public void setApp(String app) {
        this.app = app;
    }

    public String getType() {
        return type;
    }
    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    public String getGroup() {
        return group;
    }
    @JsonProperty("group")
    public void setGroup(String group) {
        this.group = group;
    }

    public String getPreview() {
        return preview;
    }
    @JsonProperty("preview")
    public void setPreview(String preview) {
        this.preview = preview;
    }

    public String getAuthorSex() {
        return authorSex;
    }
    @JsonProperty("author_sex")
    public void setAuthorSex(String authorSex) {
        this.authorSex = authorSex;
    }

    public int getRelatedCount() {
        return relatedCount;
    }

    @JsonProperty("related_count")
    public void setRelatedCount(int relatedCount) {
        this.relatedCount = relatedCount;
    }

    @JsonProperty("author_group")
    public String getAuthorGroup() {
        return authorGroup;
    }

    public void setAuthorGroup(String authorGroup) {
        this.authorGroup = authorGroup;
    }

    public long getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(long id) {
        this.id = id;
    }

    public String getTags() {
        return tags;
    }

    @JsonProperty("tags")
    public void setTags(String tags) {
        this.tags = tags;
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

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
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

    @JsonProperty("source_url")
    public void setSource(String source) {
        this.source = source;
    }

    public String getAuthor() {
        return author;
    }
    @JsonProperty("author")
    public void setAuthor(String author) {
        this.author = author;
    }

    public int getVoteCount() {
        return voteCount;
    }

    @JsonProperty("vote_count")
    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    @JsonProperty("comment_count")
    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public boolean isAdult() {
        return adult;
    }
    @JsonProperty("plus18")
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

    @JsonProperty("report_count")
    public void setReportCount(int reportCount) {
        this.reportCount = reportCount;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }



}
