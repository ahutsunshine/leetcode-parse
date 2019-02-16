package com.leetcode.model.login;

import java.util.List;
import java.util.Map;

public class Label {
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

    public String getHelpText() {
        return help_text;
    }

    public void setHelpText(String help_text) {
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
