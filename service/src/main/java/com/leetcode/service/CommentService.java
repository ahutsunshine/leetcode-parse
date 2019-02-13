package com.leetcode.service;

import com.leetcode.model.comment.CommentBody;
import com.leetcode.model.response.APIResponse;

public interface CommentService {
    APIResponse addComment(CommentBody request);

    APIResponse updateComment(CommentBody request);
}
