package com.example.jcj.learningskid;

import java.io.Serializable;

public class EnglishGrammar implements Serializable{
    private int id;
    private String name;
    private String content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EnglishGrammar() {
    }

    public EnglishGrammar(int id, String name, String content) {
        this.id = id;
        this.name = name;
        this.content = content;
    }

    public EnglishGrammar(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
