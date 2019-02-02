package com.leetcode.model.discuss;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.leetcode.model.user.User;

public class TopicDetail {
    private Integer id;
    private Integer voteCount;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer voteStatus;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String content;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long updationDate;
    private Long creationDate;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String status;
    private User author;
    private Boolean isOwnPost;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public long getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(long creationDate) {
        this.creationDate = creationDate;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getVoteStatus() {
        return voteStatus;
    }

    public void setVoteStatus(Integer voteStatus) {
        this.voteStatus = voteStatus;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getUpdationDate() {
        return updationDate;
    }

    public void setUpdationDate(Long updationDate) {
        this.updationDate = updationDate;
    }

    @JsonProperty("isOwnPost")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Boolean getOwnPost() {
        return isOwnPost;
    }

    public void setOwnPost(Boolean ownPost) {
        isOwnPost = ownPost;
    }
}
