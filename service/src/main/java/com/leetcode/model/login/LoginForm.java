package com.leetcode.model.login;

import java.util.List;

public class LoginForm {
    private LoginField fields;
    private List<String> field_order;
    private List<String> errors;

    public LoginField getFields() {
        return fields;
    }

    public void setFields(LoginField fields) {
        this.fields = fields;
    }

    public List<String> getField_order() {
        return field_order;
    }

    public void setField_order(List<String> field_order) {
        this.field_order = field_order;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
