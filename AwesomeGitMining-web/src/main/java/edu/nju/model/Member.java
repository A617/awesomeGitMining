package edu.nju.model;

public class Member {
    private Integer userid;

    private String username;

    private String password;

    private String member_email;

    public Member(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public Member(String username, String password,String member_email) {
        this.username = username;
        this.password = password;
        this.member_email = member_email;
    }

    public Member() {

    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getMember_email() {
        return member_email;
    }

    public void setMember_email(String member_email) {
        this.member_email = member_email == null ? null : member_email.trim();
    }
}