package com.leetcode.model.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
    private Boolean isDiscussAdmin;
    private Boolean isDiscussStaff;
    private String username;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer reputation;
    private Profile profile;

    @JsonProperty("isDiscussAdmin")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Boolean getDiscussAdmin() {
        return isDiscussAdmin;
    }

    public void setDiscussAdmin(Boolean discussAdmin) {
        isDiscussAdmin = discussAdmin;
    }

    @JsonProperty("isDiscussStaff")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Boolean getDiscussStaff() {
        return isDiscussStaff;
    }

    public void setDiscussStaff(Boolean discussStaff) {
        isDiscussStaff = discussStaff;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getReputation() {
        return reputation;
    }

    public void setReputation(Integer reputation) {
        this.reputation = reputation;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    static class Profile {
        private String userSlug;
        private String userAvatar;

        public String getUserSlug() {
            return userSlug;
        }

        public void setUserSlug(String userSlug) {
            this.userSlug = userSlug;
        }

        public String getUserAvatar() {
            return userAvatar;
        }

        public void setUserAvatar(String userAvatar) {
            this.userAvatar = userAvatar;
        }
    }
}
