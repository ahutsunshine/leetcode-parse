package com.leetcode.model.discuss;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

public class Topic {
    private String id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String title;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer commentCount;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer topLevelCommentCount;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Boolean subscribed;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer viewCount;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> tags;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private TopicDetail post;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public TopicDetail getPost() {
        return post;
    }

    public void setPost(TopicDetail post) {
        this.post = post;
    }

    public Integer getTopLevelCommentCount() {
        return topLevelCommentCount;
    }

    public void setTopLevelCommentCount(Integer topLevelCommentCount) {
        this.topLevelCommentCount = topLevelCommentCount;
    }

    public Boolean getSubscribed() {
        return subscribed;
    }

    public void setSubscribed(Boolean subscribed) {
        this.subscribed = subscribed;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
