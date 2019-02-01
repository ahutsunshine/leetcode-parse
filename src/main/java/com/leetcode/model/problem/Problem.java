package com.leetcode.model.problem;

import java.util.List;
import java.util.Map;

/**
 * 问题详情，json数据可参考resources/problem/problem_detail.json
 */
public class Problem {
    private String questionId;
    private String questionFrontendId;
    private String boundTopicId;
    private String title; //标题，如Two-Sum
    private String titleSlug;//标题段，如two-sum，
    private String content;//问题内容
    private String difficulty;//难以程度
    private Integer likes;
    private Integer dislikes;
    private Boolean isLiked;
    private String similarQuestions;//相似的题目
    private List<Contributor> contributors;
    private List<TopicTag> topicTags;//相关话题
    private List<CodeSnippet> codeSnippets;//语言方法模板
    private String stats; //提交状态
    private List<String> hints;
    private Map<String, Object> solution;
    private String metaData;//方法签名

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getQuestionFrontendId() {
        return questionFrontendId;
    }

    public void setQuestionFrontendId(String questionFrontendId) {
        this.questionFrontendId = questionFrontendId;
    }

    public String getBoundTopicId() {
        return boundTopicId;
    }

    public void setBoundTopicId(String boundTopicId) {
        this.boundTopicId = boundTopicId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleSlug() {
        return titleSlug;
    }

    public void setTitleSlug(String titleSlug) {
        this.titleSlug = titleSlug;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Integer getDislikes() {
        return dislikes;
    }

    public void setDislikes(Integer dislikes) {
        this.dislikes = dislikes;
    }

    public Boolean getLiked() {
        return isLiked;
    }

    public void setLiked(Boolean liked) {
        isLiked = liked;
    }

    public String getSimilarQuestions() {
        return similarQuestions;
    }

    public void setSimilarQuestions(String similarQuestions) {
        this.similarQuestions = similarQuestions;
    }

    public List<Contributor> getContributors() {
        return contributors;
    }

    public void setContributors(List<Contributor> contributors) {
        this.contributors = contributors;
    }

    public List<TopicTag> getTopicTags() {
        return topicTags;
    }

    public void setTopicTags(List<TopicTag> topicTags) {
        this.topicTags = topicTags;
    }

    public List<CodeSnippet> getCodeSnippets() {
        return codeSnippets;
    }

    public void setCodeSnippets(List<CodeSnippet> codeSnippets) {
        this.codeSnippets = codeSnippets;
    }

    public String getStats() {
        return stats;
    }

    public void setStats(String stats) {
        this.stats = stats;
    }

    public List<String> getHints() {
        return hints;
    }

    public void setHints(List<String> hints) {
        this.hints = hints;
    }

    public Map<String, Object> getSolution() {
        return solution;
    }

    public void setSolution(Map<String, Object> solution) {
        this.solution = solution;
    }

    public String getMetaData() {
        return metaData;
    }

    public void setMetaData(String metaData) {
        this.metaData = metaData;
    }
}
