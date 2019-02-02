package com.leetcode.model.discuss;

import com.fasterxml.jackson.annotation.JsonInclude;

public class Topic {
    private String id;
    private String title;
    private Integer commentCount;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer topLevelCommentCount;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Boolean subscribed;
    private Integer viewCount;
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
}
