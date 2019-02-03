package com.leetcode.model.problem.list;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Map;

public class ProblemUserStatus {
    private ProblemState stat;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String status;
    private Map<String, Object> difficulty;
    private Boolean paid_only;
    private Boolean is_favor;
    private Integer frequency;
    private Integer progress;

    public ProblemState getStat() {
        return stat;
    }

    public void setStat(ProblemState stat) {
        this.stat = stat;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Map<String, Object> getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Map<String, Object> difficulty) {
        this.difficulty = difficulty;
    }

    public Boolean getPaidOnly() {
        return paid_only;
    }

    public void setPaidOnly(Boolean paid_only) {
        this.paid_only = paid_only;
    }

    public Boolean getFavor() {
        return is_favor;
    }

    public void setFavor(Boolean is_favor) {
        this.is_favor = is_favor;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }
}
