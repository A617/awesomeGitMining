package edu.nju.model;

/**
 * Created by tj on 2016/5/6.
 */
public class Recommender {
    /**
     * 推荐的项目的fullName
     */
    private String repository;
    /**
     * 搜索的记录
     */
    private String keyword;

    public Recommender(String repository, String keyword) {
        this.repository = repository;
        this.keyword = keyword;
    }

    public String getRepository() {
        return repository;
    }

    public void setRepository(String repository) {
        this.repository = repository;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
