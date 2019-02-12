package com.leetcode.model.login;

import java.util.List;
import java.util.Map;

public class LoginField {
    private Label login;
    private Label password;

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

    static class Label{
        private String label;
        private String value;
        private String help_text;
        private List<String> errors;
        private Map<String, Object> widget;

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getHelp_text() {
            return help_text;
        }

        public void setHelp_text(String help_text) {
            this.help_text = help_text;
        }

        public List<String> getErrors() {
            return errors;
        }

        public void setErrors(List<String> errors) {
            this.errors = errors;
        }

        public Map<String, Object> getWidget() {
            return widget;
        }

        public void setWidget(Map<String, Object> widget) {
            this.widget = widget;
        }
    }
}
