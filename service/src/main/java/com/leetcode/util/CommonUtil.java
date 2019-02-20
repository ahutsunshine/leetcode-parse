package com.leetcode.util;

import com.leetcode.common.PageReqBody;
import com.leetcode.model.response.APIResponse;
import org.springframework.util.StringUtils;

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

    public static APIResponse checkPageParam(PageReqBody req) {
        if (StringUtils.isEmpty(req.getUri())) return new APIResponse(400, "Uri is required.");
        if (req.getId() == null) return new APIResponse(400, "Question id is required.");
        if (req.getPage() < 0) return new APIResponse(400, "Negative page index is not supported.");
        if (req.getPage() == 0) req.setPage(1);
        if (req.getPageSize() <= 0 || req.getPageSize() > 512) req.setPageSize(15);
        return null;
    }
}
