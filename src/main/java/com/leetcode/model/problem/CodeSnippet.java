package com.leetcode.model.problem;

/**
 * 不同语言对应的模板方法
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
