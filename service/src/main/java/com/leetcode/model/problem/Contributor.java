package com.leetcode.model.problem;

import com.fasterxml.jackson.annotation.JsonInclude;

public class Contributor {
    private String username;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String profileUrl;
    private String avatarUrl;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }
}
