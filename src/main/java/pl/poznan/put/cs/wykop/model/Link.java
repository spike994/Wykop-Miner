package pl.poznan.put.cs.wykop.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import pl.poznan.put.cs.wykop.dao.TagDAO;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by dk994 on 23.02.15.
 */
@JsonIgnoreProperties({"author_avatar", "author_avatar_big", "author_avatar_med", "author_avatar_lo", "user_vote", "user_favorite", "user_observe", "user_lists", "status", "can_vote",
        "is_hot", "violation_url", "info", "own_content",
})
@Entity
@Table(name = "link")
public class Link {
    @Id
    @Column(name = "id")
    private long id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name ="url")
    private String url;
    @Column(name = "date")
    private Date date;
    @Column(name = "source")
    private String source;
    @Column(name = "author")
    private String author;
    @Column(name = "vote_count")
    private long voteCount;
    @Column(name = "comment_count")
    private long commentCount;
    @Column(name = "related_count")
    private long relatedCount;
    @Column(name = "author_group")
    private String authorGroup;
    @Column(name = "adult")
    private boolean adult;
    @Column(name = "tags")
    private String tags;
    @Column(name = "category")
    private String category;
    @Column(name = "report_count")
    private long reportCount;
    @Column(name = "author_sex")
    private String authorSex;
    @Column(name = "type")
    private String type;
    @Column(name = "group_name")
    private String group;
    @Column(name = "preview")
    private String preview;
    @Column(name = "has_own_content")
    private boolean hasOwnContent;
    @Column(name = "category_name")
    private String categoryName;
    @Column(name = "app")
    private String app;
    @OneToMany(mappedBy = "link", cascade = CascadeType.ALL)
    private List<LinkComment> comments;
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name="link_tag",
            joinColumns={@JoinColumn(name="link_id")},
            inverseJoinColumns={@JoinColumn(name="tag_id")})
    private Set<Tag> tagList;

    public List<LinkComment> getComments() {
        return comments;
    }

    public void setComments(List<LinkComment> comments) {
        this.comments = comments;
    }

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

    public long getRelatedCount() {
        return relatedCount;
    }

    @JsonProperty("related_count")
    public void setRelatedCount(long relatedCount) {
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

    public long getVoteCount() {
        return voteCount;
    }

    @JsonProperty("vote_count")
    public void setVoteCount(long voteCount) {
        this.voteCount = voteCount;
    }

    public long getCommentCount() {
        return commentCount;
    }

    @JsonProperty("comment_count")
    public void setCommentCount(long commentCount) {
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

    public long getReportCount() {
        return reportCount;
    }

    @JsonProperty("report_count")
    public void setReportCount(long reportCount) {
        this.reportCount = reportCount;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public void inflateTags(TagDAO tagDAO){
        Pattern pattern = Pattern.compile("(?<![^\\s]+)#[a-zA-Z0-9]+");
        if(tags!=null) {
            Matcher matcher = pattern.matcher(tags);
            tagList = new HashSet<Tag>();
            while (matcher.find()) {
                String name = matcher.group();
                Tag tag = tagDAO.getTag(name);
                tagList.add(tag);
                tag.setName(matcher.group());
            }
        }
    }


}
