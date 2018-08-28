package com.example.bogdan.newsapp.Model;

import java.util.ArrayList;
import java.util.List;

public class Website {


    private String status;
    private List<Article> articles = new ArrayList<>();

    public Website(){

    }

    public Website(String status,List<Article> articles) {
        this.status = status;
        this.articles = articles;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setSources(List<Source> sources) {
        this.articles = articles;
    }
}
