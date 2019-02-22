package com.leetcode.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.leetcode.common.PageReqBody;
import com.leetcode.common.ResponseStatus;
import com.leetcode.model.comment.Comment;
import com.leetcode.model.comment.CommentReqBody;
import com.leetcode.model.response.APIResponse;
import com.leetcode.service.CommentService;
import org.apache.http.HttpEntity;
import org.apache.http.client.CookieStore;
import org.apache.http.entity.StringEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.leetcode.util.CommonUtil.*;
import static com.leetcode.util.HttpUtil.*;
import static com.leetcode.util.RequestParamUtil.buildCommentReqBody;


@Service
public class CommentServiceImpl implements CommentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommentServiceImpl.class);

    private static final String CREATE_COMMENT_OPERATION = "createComment";
    private static final String UPDATE_COMMENT_OPERATION = "updateComment";
    private static final String DELETE_COMMENT_OPERATION = "deleteComment";

    @Override
    public APIResponse getComments(PageReqBody req) {
        APIResponse errorStatus = checkPageParam(req);
        if (errorStatus != null) return errorStatus;
//        CookieStore cookieStore = getCookies(req.getUri());
        StringEntity requestBody = buildCommentReqBody(req);
        String res = post(requestBody, req.getCookies());
        APIResponse error = getErrorIfFailed(res);
        if (error != null) return error;
        JSONArray j = JSONObject.parseObject(res).getJSONObject("data")
                .getJSONObject("topicComments").getJSONArray("data");
        if (j == null || j.size() == 0) return new APIResponse(new ArrayList<>());
        List<Comment> comments = JSON.parseArray(j.toString(), Comment.class);
        return new APIResponse(comments);
    }

    @Override
    public APIResponse createComment(CommentReqBody req) {
        APIResponse error = checkParams(req, CREATE_COMMENT_OPERATION);
        if (error != null) return error;
        StringEntity requestBody = buildCommentReqBody(req.getTopicId(), req.getParentCommentId(), req.getContent());
        return commentPost(req, requestBody, CREATE_COMMENT_OPERATION);
    }

    @Override
    public APIResponse updateComment(CommentReqBody req) {
        APIResponse error = checkParams(req, UPDATE_COMMENT_OPERATION);
        if (error != null) return error;
        StringEntity requestBody = buildCommentReqBody(req.getCommentId(), req.getContent());
        return commentPost(req, requestBody, UPDATE_COMMENT_OPERATION);
    }

    @Override
    public APIResponse deleteComment(CommentReqBody req) {
        APIResponse error = checkParams(req, DELETE_COMMENT_OPERATION);
        if (error != null) return error;
        StringEntity requestBody = buildCommentReqBody(req.getCommentId());
        return commentPost(req, requestBody, DELETE_COMMENT_OPERATION);
    }

    private APIResponse commentPost(CommentReqBody req, HttpEntity entity, String operationName) {
        String cookies = req.getCookies();
        String res = post(req.getUri(), cookies, entity);
        APIResponse e = getErrorIfFailed(res);
        if (e != null) return e;
        return getResponseStatus(operationName, res);
    }

    private APIResponse checkParams(CommentReqBody req, String operation) {
        if (!isCookieValid(req.getCookies())) {
            return new APIResponse(400, "User cookie is invalid");
        }
        if (StringUtils.isEmpty(req.getUri())) {
            return new APIResponse(400, "Refer uri is required");
        }
        if (CREATE_COMMENT_OPERATION.equals(operation) && req.getTopicId() == null) {
            return new APIResponse(400, "Topic id is required");
        }
        if ((UPDATE_COMMENT_OPERATION.equals(operation)
                || DELETE_COMMENT_OPERATION.equals(operation))
                && req.getCommentId() == null) {
            return new APIResponse(400, "Comment id is required");
        }
        if (StringUtils.isEmpty(req.getContent()) && !DELETE_COMMENT_OPERATION.equals(operation)) {
            return new APIResponse(400, "Comment content is required");
        }
        return null;
    }
}
