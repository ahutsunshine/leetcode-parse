package com.leetcode.model.discuss;

import java.util.List;

public class DiscussTopics {
    private Integer totalNum;
    private List<Discuss> edges;

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public List<Discuss> getEdges() {
        return edges;
    }

    public void setEdges(List<Discuss> edges) {
        this.edges = edges;
    }
}
