package com.leetcode.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.leetcode.model.comment.CommentReqBody;
import com.leetcode.common.ResponseStatus;
import com.leetcode.model.response.APIResponse;
import com.leetcode.service.CommentService;
import org.apache.http.HttpEntity;
import org.apache.http.entity.StringEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import static com.leetcode.util.CommonUtil.isCookieValid;
import static com.leetcode.util.HttpUtil.getErrorIfFailed;
import static com.leetcode.util.HttpUtil.post;
import static com.leetcode.util.RequestParamUtil.buildCommentReqBody;


@Service
public class CommentServiceImpl implements CommentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommentServiceImpl.class);

    private static final String CREATE_COMMENT_OPERATION = "createComment";
    private static final String UPDATE_COMMENT_OPERATION = "updateComment";
    private static final String DELETE_COMMENT_OPERATION = "deleteComment";

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
        JSONObject data = JSONObject.parseObject(res).getJSONObject("data");
        data = data.getJSONObject(operationName);
        ResponseStatus status = JSON.parseObject(data.toString(), ResponseStatus.class);
        return new APIResponse(status);
    }

    private APIResponse checkParams(CommentReqBody req, String operation) {
        if (!isCookieValid(req.getCookies())) {
            return new APIResponse(400, "User cookie is invalid.");
        }
        if (StringUtils.isEmpty(req.getUri())) {
            return new APIResponse(400, "Refer uri is required.");
        }
        if (CREATE_COMMENT_OPERATION.equals(operation) && req.getTopicId() == null) {
            return new APIResponse(400, "Topic id is required.");
        }
        if ((UPDATE_COMMENT_OPERATION.equals(operation)
                || DELETE_COMMENT_OPERATION.equals(operation))
                && req.getCommentId() == null) {
            return new APIResponse(400, "Comment id is required.");
        }
        if (StringUtils.isEmpty(req.getContent()) && !DELETE_COMMENT_OPERATION.equals(operation)) {
            return new APIResponse(400, "Comment content is required.");
        }
        return null;
    }
}
