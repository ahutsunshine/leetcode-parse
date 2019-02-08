package com.leetcode.model.problem.detail;

/**
 * Algorithmic template method corresponding to different coding languages
 */
public class CodeSnippet {
    private String lang;
    private String langSlug;
    private String code;

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getLangSlug() {
        return langSlug;
    }

    public void setLangSlug(String langSlug) {
        this.langSlug = langSlug;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
