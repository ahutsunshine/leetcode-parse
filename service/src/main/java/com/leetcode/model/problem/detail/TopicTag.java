package com.leetcode.model.problem.detail;

import com.fasterxml.jackson.annotation.JsonInclude;

public class TopicTag {
    private String name;
    private String slug;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String translatedName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getTranslatedName() {
        return translatedName;
    }

    public void setTranslatedName(String translatedName) {
        this.translatedName = translatedName;
    }

}
