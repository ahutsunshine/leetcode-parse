package com.leetcode.model.login;

public class LoginResponse {
    private String location;
    private LoginForm form;
    private String html;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LoginForm getForm() {
        return form;
    }

    public void setForm(LoginForm form) {
        this.form = form;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }
}
