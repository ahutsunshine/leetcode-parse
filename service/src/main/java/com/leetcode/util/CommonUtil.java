package com.leetcode.util;

public class CommonUtil {
    public static Boolean isCookieValid(String cookie) {
        boolean containToken = false, containSession = false;
        String[] values = cookie.split(";");
        for (String val : values) {
            String[] data = val.split("=");
            if (data.length != 2) return false; // incorrect cookie
            //remove blank space
            if (data[0].replace(" ", "").equals("csrftoken")) {
                containToken = true;
            }
            if (data[0].replace(" ", "").equals("LEETCODE_SESSION")) {
                containSession = true;
            }
        }
        return containToken && containSession;
    }
}
