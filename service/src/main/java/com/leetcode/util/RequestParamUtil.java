package com.leetcode.util;

import com.alibaba.fastjson.JSONObject;
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

    public static StringEntity buildDiscussReqBody(String orderBy, String query, Integer skip,
                                                   String pageSize, Integer questionId) {
        String operationName = "questionTopicsList";
        JSONObject variables = new JSONObject();
        variables.put("orderBy", orderBy);
        variables.put("query", query);
        variables.put("skip", skip);
        variables.put("first", pageSize);
        variables.put("tags", new ArrayList<>());
        variables.put("questionId", String.valueOf(questionId));
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

    public static StringEntity buildCommentReqBody(int topicId, Integer parentCommentId, String content) {
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
}
