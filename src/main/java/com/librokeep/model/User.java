package com.librokeep.model;

public class User {

    private int uid;
    private String username;
    private String password;
    private String role;

    public User(int uid, String username, String password, String role) {
        this.uid = uid;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public int getUid() {
        return uid;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }
}