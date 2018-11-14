package com.example.jcj.learningskid;

import java.io.Serializable;

public class EnglishNewwordLesson implements Serializable {
    private int id;
    private String name;

    public EnglishNewwordLesson(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public EnglishNewwordLesson() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
