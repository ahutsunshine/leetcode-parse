package com.leetcode.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.leetcode.model.comment.Comment;
import com.leetcode.model.discuss.Topic;

public class ResponseStatus {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Boolean ok;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer commentId;
    private String error;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Comment comment;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Topic topic;

    public Boolean getOk() {
        return ok;
    }

    public void setOk(Boolean ok) {
        this.ok = ok;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }
}
