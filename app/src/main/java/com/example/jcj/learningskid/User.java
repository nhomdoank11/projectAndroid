package com.example.jcj.learningskid;

import java.io.Serializable;

public class User implements Serializable {

    String id;
    String userName;
    String passWord;
    String email;

    public User(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User(String id, String userName, String passWord, String email) {
        this.id = id;
        this.userName = userName;
        this.passWord = passWord;
        this.email = email;
    }
}
