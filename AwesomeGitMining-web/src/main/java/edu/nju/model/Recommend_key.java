package edu.nju.model;

import org.springframework.stereotype.*;

public class Recommend_key {
    private Repository repo;

    private String keyword;


    public Recommend_key(){

    }
    public Recommend_key(Repository repo, String keyword) {
        this.repo = repo;
        this.keyword = keyword;
    }

    public Repository getRepo() {
        return repo;
    }

    public void setRepo(Repository repo) {
        this.repo = repo;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}