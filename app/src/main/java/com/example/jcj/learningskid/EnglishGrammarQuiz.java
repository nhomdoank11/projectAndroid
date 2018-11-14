package com.example.jcj.learningskid;

import java.io.Serializable;

public class EnglishGrammarQuiz implements Serializable {
    private int id;
    private String name;
    private int passCondition;
    //status pass/fail
    private String status;

    public EnglishGrammarQuiz(int id, String name, int passCondition, String status) {
        this.id = id;
        this.name = name;
        this.passCondition = passCondition;
        this.status = status;
    }

    public EnglishGrammarQuiz() {
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

    public int getPassCondition() {
        return passCondition;
    }

    public void setPassCondition(int passCondition) {
        this.passCondition = passCondition;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
