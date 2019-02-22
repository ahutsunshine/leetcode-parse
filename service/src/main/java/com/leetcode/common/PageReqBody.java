package com.leetcode.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PageReqBody {
    private static final Logger LOGGER = LoggerFactory.getLogger(PageReqBody.class);

    private String uri;
    private Integer page = 1;
    private String orderBy = "most_votes";
    private String query = "";
    private Integer pageSize = 15;
    private Integer id;
    private String cookies;

    public PageReqBody() {
        uri = "https://leetcode.com/problems";
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCookies() {
        return cookies;
    }

    public void setCookies(String cookies) {
        this.cookies = cookies;
    }
}
