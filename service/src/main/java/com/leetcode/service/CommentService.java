package com.leetcode.service;

import com.leetcode.model.comment.CommentReqBody;
import com.leetcode.model.response.APIResponse;

public interface CommentService {
    APIResponse createComment(CommentReqBody request);

    APIResponse updateComment(CommentReqBody request);

    APIResponse deleteComment(CommentReqBody request);
}
