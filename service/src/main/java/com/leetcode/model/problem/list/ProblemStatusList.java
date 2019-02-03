package com.leetcode.model.problem.list;

import java.util.List;

public class ProblemStatusList {
    private String user_name;
    private String category_slug;
    private Integer num_solved;
    private Integer num_total;
    private Integer ac_easy;
    private Integer ac_medium;
    private Integer ac_hard;
    private Integer frequency_high;
    private Integer frequency_mid;
    private List<ProblemUserStatus> stat_status_pairs;

    public String getUserName() {
        return user_name;
    }

    public void setUserName(String user_name) {
        this.user_name = user_name;
    }

    public String getCategorySlug() {
        return category_slug;
    }

    public void setCategorySlug(String category_slug) {
        this.category_slug = category_slug;
    }

    public Integer getNumSolved() {
        return num_solved;
    }

    public void setNumSolved(Integer num_solved) {
        this.num_solved = num_solved;
    }

    public Integer getNumTotal() {
        return num_total;
    }

    public void setNumTotal(Integer num_total) {
        this.num_total = num_total;
    }

    public Integer getAcEasy() {
        return ac_easy;
    }

    public void setAcEasy(Integer ac_easy) {
        this.ac_easy = ac_easy;
    }

    public Integer getAcMedium() {
        return ac_medium;
    }

    public void setAcMedium(Integer ac_medium) {
        this.ac_medium = ac_medium;
    }

    public Integer getAcHard() {
        return ac_hard;
    }

    public void setAcHard(Integer ac_hard) {
        this.ac_hard = ac_hard;
    }

    public Integer getFrequencyHigh() {
        return frequency_high;
    }

    public void setFrequencyHigh(Integer frequency_high) {
        this.frequency_high = frequency_high;
    }

    public Integer getFrequencyMid() {
        return frequency_mid;
    }

    public void setFrequencyMid(Integer frequency_mid) {
        this.frequency_mid = frequency_mid;
    }

    public List<ProblemUserStatus> getStatStatusPairs() {
        return stat_status_pairs;
    }

    public void setStatStatusPairs(List<ProblemUserStatus> stat_status_pairs) {
        this.stat_status_pairs = stat_status_pairs;
    }
}
