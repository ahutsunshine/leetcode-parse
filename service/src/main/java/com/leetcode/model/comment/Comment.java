package com.leetcode.model.comment;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.leetcode.model.discuss.TopicDetail;

public class Comment {
    private Integer id;
    private TopicDetail post;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer numChildren;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TopicDetail getPost() {
        return post;
    }

    public void setPost(TopicDetail post) {
        this.post = post;
    }

    public Integer getNumChildren() {
        return numChildren;
    }

    public void setNumChildren(Integer numChildren) {
        this.numChildren = numChildren;
    }
}
