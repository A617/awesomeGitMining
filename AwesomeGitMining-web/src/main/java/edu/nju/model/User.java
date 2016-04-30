package edu.nju.model;

/**
 * Created by Dora on 2016/4/29.
 */
public class User {

    private int id;
    private String login;
    private String company;

    public User(){}

    public User(int id, String login, String company) {
        this.id = id;
        this.login = login;
        this.company = company;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
