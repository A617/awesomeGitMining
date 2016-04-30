package edu.nju.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

public class Member {
    private String userid;

    private String username;

    private String password;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    @NotEmpty(message = "用户名不能为空")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    @Size(min = 6,max = 20,message = "密码应在6-20位之间")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }
}