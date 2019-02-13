package com.leetcode.model.comment;

import com.fasterxml.jackson.annotation.JsonInclude;

public class CommentStatus {
    private Boolean ok;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer commentId;
    private String error;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Comment comment;

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
}
