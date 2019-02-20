package com.leetcode.util;

import com.alibaba.fastjson.JSONObject;
import com.leetcode.common.PageReqBody;
import com.leetcode.model.discuss.TopicReqBody;
import org.apache.http.entity.StringEntity;

import java.util.ArrayList;

import static com.leetcode.util.HttpUtil.buildRequestBody;

public class RequestParamUtil {
    public static StringEntity buildProblemReqBody(String titleSlug) {
        String operationName = "questionData";
        JSONObject variables = new JSONObject();
        variables.put("titleSlug", titleSlug);
        String query = "query questionData($titleSlug: String!) {\n" +
                "question(titleSlug: $titleSlug) {\n" +
                "questionId\n" +
                "questionFrontendId\n" +
                "boundTopicId\n" +
                "title\n" +
                "titleSlug\n" +
                "content\n" +
                "difficulty\n" +
                "likes\n" +
                "dislikes\n" +
                "isLiked\n" +
                "similarQuestions\n" +
                "contributors {\n" +
                "username\n" +
                "profileUrl\n" +
                "avatarUrl\n" +
                "}\n" +
                "topicTags {\n" +
                "name\n" +
                "slug\n" +
                "translatedName\n" +
                "}\n" +
                "codeSnippets {\n" +
                "lang\n" +
                "langSlug\n" +
                "code\n" +
                "}\n" +
                "stats\n" +
                "hints\n" +
                "solution {\n" +
                "id\n" +
                "canSeeDetail\n" +
                "}\n" +
                "status\n" +
                "sampleTestCase\n" +
                "metaData\n" +
                "}\n" +
                "}";
        return buildRequestBody(operationName, variables.toString(), query);
    }

    public static StringEntity buildDiscussReqBody(PageReqBody req) {
        String operationName = "questionTopicsList";
        JSONObject variables = new JSONObject();
        variables.put("orderBy", req.getOrderBy());
        variables.put("query", req.getQuery());
        variables.put("skip", (req.getPage() - 1) * req.getPageSize());
        variables.put("first", req.getPageSize());
        variables.put("tags", new ArrayList<>());
        variables.put("questionId", String.valueOf(req.getId()));
        String q = "query questionTopicsList($questionId: String!, $orderBy: TopicSortingOption, $skip: Int, $query: String, $first: Int!, $tags: [String!]) {\n" +
                "questionTopicsList(questionId: $questionId, orderBy: $orderBy, skip: $skip, query: $query, first: $first, tags: $tags) {\n" +
                "...TopicsList\n" +
                "}\n" +
                "}\n" +
                "fragment TopicsList on TopicConnection {\n" +
                "totalNum\n" +
                "edges {\n" +
                "node {\n" +
                "id\n" +
                "title\n" +
                "commentCount\n" +
                "viewCount\n" +
                "\n" +
                "post {\n" +
                "id\n" +
                "voteCount\n" +
                "creationDate\n" +
                "author {\n" +
                "username\n" +
                "profile {\n" +
                "userSlug\n" +
                "userAvatar\n" +
                "}\n" +
                "}\n" +
                "status\n" +
                "}\n" +
                "}\n" +
                "cursor\n" +
                "}\n" +
                "}\n";
        return buildRequestBody(operationName, variables.toString(), q);
    }

    public static StringEntity buildDiscussTopicsReqBody(int topicId) {
        String operationName = "DiscussTopic";
        JSONObject variables = new JSONObject();
        variables.put("topicId", topicId);
        String q = "query DiscussTopic($topicId: Int!) {\n" +
                "topic(id: $topicId) {\n" +
                "id\n" +
                "viewCount\n" +
                "topLevelCommentCount\n" +
                "subscribed\n" +
                "title\n" +
                "pinned\n" +
                "tags\n" +
                "post {\n" +
                "...DiscussPost\n" +
                "}\n" +
                "}\n" +
                "}\n" +
                "fragment DiscussPost on PostNode {\n" +
                "id\n" +
                "voteCount\n" +
                "voteStatus\n" +
                "content\n" +
                "updationDate\n" +
                "creationDate\n" +
                "status\n" +
                "author {\n" +
                "isDiscussAdmin\n" +
                "isDiscussStaff\n" +
                "username\n" +
                "profile {\n" +
                "userAvatar\n" +
                "reputation\n" +
                "userSlug\n" +
                "}\n" +
                "}\n" +
                "isOwnPost\n" +
                "}\n";
        return buildRequestBody(operationName, variables.toString(), q);
    }

    public static StringEntity buildCommentReqBody(Integer topicId, Integer parentCommentId, String content) {
        String operationName = "createComment";
        JSONObject variables = new JSONObject();
        variables.put("topicId", topicId);
        variables.put("parentCommentId", parentCommentId);
        variables.put("content", content);
        String q = "mutation createComment($topicId: Int!, $parentCommentId: Int!, $content: String!) {\n" +
                "createComment(topicId: $topicId, parentCommentId: $parentCommentId, content: $content) {\n" +
                "ok\n" +
                "commentId\n" +
                "error\n" +
                "comment {\n" +
                "id\n" +
                "post {\n" +
                "...DiscussPost\n" +
                "}\n" +
                "}\n" +
                "}\n" +
                "}\n" +
                "\n" +
                "fragment DiscussPost on PostNode {\n" +
                "id\n" +
                "voteCount\n" +
                "voteStatus\n" +
                "content\n" +
                "updationDate\n" +
                "creationDate\n" +
                "status\n" +
                "author {\n" +
                "isDiscussAdmin\n" +
                "isDiscussStaff\n" +
                "username\n" +
                "profile {\n" +
                "userAvatar\n" +
                "reputation\n" +
                "userSlug\n" +
                "}\n" +
                "}\n" +
                "isOwnPost\n" +
                "}";
        return buildRequestBody(operationName, variables.toString(), q);
    }

    public static StringEntity buildCommentReqBody(Integer commentId, String content) {
        String operationName = "updateComment";
        JSONObject variables = new JSONObject();
        variables.put("commentId", commentId);
        variables.put("content", content);
        String q = "mutation updateComment($commentId: Int!, $content: String!) {\n" +
                "updateComment(id: $commentId, content: $content) {\n" +
                "ok\n" +
                "error\n" +
                "comment {\n" +
                "id\n" +
                "post {\n" +
                "id\n" +
                "content\n" +
                "updationDate\n" +
                "}\n" +
                "}\n" +
                "}\n" +
                "}";
        return buildRequestBody(operationName, variables.toString(), q);
    }

    public static StringEntity buildCommentReqBody(Integer commentId) {
        String operationName = "deleteComment";
        JSONObject variables = new JSONObject();
        variables.put("commentId", commentId);
        String q = "mutation deleteComment($commentId: Int!) {\n" +
                "deleteComment(id: $commentId) {\n" +
                "ok\n" +
                "error\n" +
                "post {\n" +
                "id\n" +
                "status\n" +
                "}\n" +
                "}\n" +
                "}";
        return buildRequestBody(operationName, variables.toString(), q);
    }

    public static StringEntity buildCommentReqBody(PageReqBody req) {
        String operationName = "discussComments";
        JSONObject variables = new JSONObject();
        variables.put("topicId", req.getId());
        variables.put("orderBy", req.getOrderBy());
        variables.put("numPerPage", req.getPageSize());
        variables.put("pageNo", req.getPage());
        String q = "query discussComments($topicId: Int!, $orderBy: String = \"newest_to_oldest\", $pageNo: Int = 1, $numPerPage: Int = 10) {\n" +
                "topicComments(topicId: $topicId, orderBy: $orderBy, pageNo: $pageNo, numPerPage: $numPerPage) {\n" +
                "data {\n" +
                "id\n" +
                "post {\n" +
                "...DiscussPost\n" +
                "}\n" +
                "numChildren\n" +
                "}\n" +
                "}\n" +
                "}\n" +
                "fragment DiscussPost on PostNode {\n" +
                "id\n" +
                "voteCount\n" +
                "voteStatus\n" +
                "content\n" +
                "updationDate\n" +
                "creationDate\n" +
                "status\n" +
                "author {\n" +
                "isDiscussAdmin\n" +
                "isDiscussStaff\n" +
                "username\n" +
                "profile {\n" +
                "userAvatar\n" +
                "reputation\n" +
                "userSlug\n" +
                "}\n" +
                "}\n" +
                "authorIsModerator\n" +
                "isOwnPost\n" +
                "}\n";
        return buildRequestBody(operationName, variables.toString(), q);
    }

    public static StringEntity buildUpdateTopicReqBody(TopicReqBody req) {
        String operationName = "updateTopic";
        JSONObject variables = new JSONObject();
        variables.put("id", req.getId());
        variables.put("title", req.getTitle());
        variables.put("content", req.getContent());
        variables.put("tags", req.getTags());
        String q = "mutation updateTopic($title: String!, $content: String!, $id: Int!, $tags: [String]) {\n" +
                "updateTopic(title: $title, content: $content, id: $id, tags: $tags) {\n" +
                "ok\n" +
                "error\n" +
                "topic {\n" +
                "id\n" +
                "title\n" +
                "tags\n" +
                "post {\n" +
                "id\n" +
                "content\n" +
                "}\n" +
                "}\n" +
                "}\n" +
                "}";
        return buildRequestBody(operationName, variables.toString(), q);
    }

    public static StringEntity buildCreateTopicReqBody(TopicReqBody req) {
        String operationName = "postTopic";
        JSONObject variables = new JSONObject();
        variables.put("questionId", String.valueOf(req.getId()));
        variables.put("title", req.getTitle());
        variables.put("content", req.getContent());
        variables.put("tags", req.getTags());
        String q = "mutation postTopic($title: String!, $content: String!, $questionId: Int!, $tags: [String!]) {\n" +
                "createTopicForQuestion(title: $title, content: $content, questionId: $questionId, tags: $tags) {\n" +
                "error\n" +
                "topic {\n" +
                "id\n" +
                "}\n" +
                "}\n" +
                "}";
        return buildRequestBody(operationName, variables.toString(), q);
    }

    public static StringEntity buildDeleteTopicReqBody(TopicReqBody req) {
        String operationName = "deleteTopic";
        JSONObject variables = new JSONObject();
        variables.put("topicId", req.getId());
        String q = "mutation deleteTopic($topicId: Int!) {\n" +
                "deleteTopic(id: $topicId) {\n" +
                "ok\n" +
                "error\n" +
                "}\n" +
                "}";
        return buildRequestBody(operationName, variables.toString(), q);
    }
}
