package com.newsworld;

import java.util.List;

public class NewsApiResponse {
    String status;
    int totalResults;
    List<HeadLine> articles;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public List<HeadLine> getArticles() {
        return articles;
    }

    public void setArticles(List<HeadLine> articles) {
        this.articles = articles;
    }
}
