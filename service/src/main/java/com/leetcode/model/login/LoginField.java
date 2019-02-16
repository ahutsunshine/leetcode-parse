package com.leetcode.model.login;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.Map;

public class LoginField {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Label login;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Label password;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Label email;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Label username;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Label password2;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Label password1;

    public Label getLogin() {
        return login;
    }

    public void setLogin(Label login) {
        this.login = login;
    }

    public Label getPassword() {
        return password;
    }

    public void setPassword(Label password) {
        this.password = password;
    }

    public Label getEmail() {
        return email;
    }

    public void setEmail(Label email) {
        this.email = email;
    }

    public Label getUsername() {
        return username;
    }

    public void setUsername(Label username) {
        this.username = username;
    }

    public Label getPassword2() {
        return password2;
    }

    public void setPassword2(Label password2) {
        this.password2 = password2;
    }

    public Label getPassword1() {
        return password1;
    }

    public void setPassword1(Label password1) {
        this.password1 = password1;
    }
}
