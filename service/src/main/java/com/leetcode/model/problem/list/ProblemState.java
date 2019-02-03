package com.leetcode.model.problem.list;

public class ProblemState {
    private Integer question_id;
    private Boolean question__article__live;
    private String question__article__slug;
    private String question__title;
    private String question__title_slug;
    private Boolean question__hide;
    private Integer total_acs;
    private Integer total_submitted;
    private Integer frontend_question_id;
    private Boolean is_new_question;

    public Integer getQuestionId() {
        return question_id;
    }

    public void setQuestionId(Integer question_id) {
        this.question_id = question_id;
    }

    public Boolean getQuestionArticleLive() {
        return question__article__live;
    }

    public void setQuestionArticleLive(Boolean question__article__live) {
        this.question__article__live = question__article__live;
    }

    public String getQuestionArticleSlug() {
        return question__article__slug;
    }

    public void setQuestionArticleSlug(String question__article__slug) {
        this.question__article__slug = question__article__slug;
    }

    public String getQuestionTitle() {
        return question__title;
    }

    public void setQuestionTitle(String question__title) {
        this.question__title = question__title;
    }

    public String getQuestionTitleSlug() {
        return question__title_slug;
    }

    public void setQuestionTitleSlug(String question__title_slug) {
        this.question__title_slug = question__title_slug;
    }

    public Boolean getQuestionHide() {
        return question__hide;
    }

    public void setQuestionHide(Boolean question__hide) {
        this.question__hide = question__hide;
    }

    public Integer getTotalAcs() {
        return total_acs;
    }

    public void setTotalAcs(Integer total_acs) {
        this.total_acs = total_acs;
    }

    public Integer getTotalSubmitted() {
        return total_submitted;
    }

    public void setTotalSubmitted(Integer total_submitted) {
        this.total_submitted = total_submitted;
    }

    public Integer getFrontendQuestionId() {
        return frontend_question_id;
    }

    public void setFrontendQuestionId(Integer frontend_question_id) {
        this.frontend_question_id = frontend_question_id;
    }

    public Boolean getNewQuestion() {
        return is_new_question;
    }

    public void setNewQuestion(Boolean is_new_question) {
        this.is_new_question = is_new_question;
    }
}
