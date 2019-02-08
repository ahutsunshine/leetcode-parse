package com.leetcode.model.problem.detail;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.Map;

/**
 * problem detail, JSON data can be referred to resources/problem/problem_detail.json
 */
public class Problem {
    private String questionId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String questionFrontendId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String boundTopicId;
    private String title; //such as Two-Sum
    private String titleSlug;//such as two-sum，
    private String content;//problem description, null may mean user needs to subscribe
    private String difficulty;//such as 'Easy','Medium' or 'Difficulty'
    private Integer likes;
    private Integer dislikes;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Boolean isLiked;
    private String similarQuestions;
    private List<Contributor> contributors;
    private List<TopicTag> topicTags;//related topics
    private List<CodeSnippet> codeSnippets;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String stats;
    private List<String> hints;
    private Map<String, Object> solution;
    private String metaData;//method signature

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
