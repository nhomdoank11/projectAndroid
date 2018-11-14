package com.example.jcj.learningskid;

import java.io.Serializable;

public class EnglishNewwordContent implements Serializable {
    private int id;
    private int LessnId;
    private String word;
    private String detailWord;

    public EnglishNewwordContent(int id, int lessnId, String word, String detailWord) {
        this.id = id;
        LessnId = lessnId;
        this.word = word;
        this.detailWord = detailWord;
    }

    public EnglishNewwordContent(int id, String word, String detailWord) {
        this.id = id;
        this.word = word;
        this.detailWord = detailWord;
    }

    public EnglishNewwordContent() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLessnId() {
        return LessnId;
    }

    public void setLessnId(int lessnId) {
        LessnId = lessnId;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getDetailWord() {
        return detailWord;
    }

    public void setDetailWord(String detailWord) {
        this.detailWord = detailWord;
    }

    @Override
    public String toString() {
        return detailWord;
    }
}
