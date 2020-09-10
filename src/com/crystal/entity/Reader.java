package com.crystal.entity;

public class Reader {
    private Integer id;
    private String username;
    private String password;
    private String name;
    private String tel;
    private String cardid;
    private String gender;
    private Integer integrity;


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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCardid() {
        return cardid;
    }

    public void setCardid(String cardid) {
        this.cardid = cardid;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public Integer getIntegrity() {
        return integrity;
    }

    public void setIntegrity(Integer integrity) {
        this.integrity = integrity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Reader(Integer id, String username, String password, String tel, String cardid, String gender) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.tel = tel;
        this.cardid = cardid;
        this.gender = gender;
    }

    public Reader(String username, String tel, String cardid) {
        this.username = username;
        this.tel = tel;
        this.cardid = cardid;
    }

    public Reader(Integer id, String username, String password, String name, String tel, String cardid, String gender) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.tel = tel;
        this.cardid = cardid;
        this.gender = gender;
    }

    public Reader(Integer id, String username, String password, String name, String tel, String cardid, String gender, Integer integrity) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.tel = tel;
        this.cardid = cardid;
        this.gender = gender;
        this.integrity = integrity;
    }

    public Reader() {}
}
