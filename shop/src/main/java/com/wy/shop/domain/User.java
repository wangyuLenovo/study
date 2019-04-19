package com.wy.shop.domain;

public class User extends BaseEntity{

    private Long uid;
    private String username;
    private String password;


    public User(Long uid, String username, String password) {
        this.uid = uid;
        this.username = username;
        this.password = password;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
