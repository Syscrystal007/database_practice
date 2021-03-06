package com.crystal.entity;

public class Admin {
    private Integer id;
    private String username;
    private String password;
    private Integer level;

    public Admin(Integer id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Admin(Integer id, String username, String password, Integer level) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.level = level;
    }

    public Admin() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
