package com.example.loginapi.Model;

public class Question {
    private String question_type,type, question_image, question_text, currentanswer;

    public Question() {
    }

    public Question(String question_type, String type, String question_image, String question_text, String currentanswer) {
        this.question_type = question_type;
        this.type = type;
        this.question_image = question_image;
        this.question_text = question_text;
        this.currentanswer = currentanswer;
    }

    public String getQuestion_type() {
        return question_type;
    }

    public void setQuestion_type(String question_type) {
        this.question_type = question_type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQuestion_image() {
        return question_image;
    }

    public void setQuestion_image(String question_image) {
        this.question_image = question_image;
    }

    public String getQuestion_text() {
        return question_text;
    }

    public void setQuestion_text(String question_text) {
        this.question_text = question_text;
    }

    public String getCurrentanswer() {
        return currentanswer;
    }

    public void setCurrentanswer(String currentanswer) {
        this.currentanswer = currentanswer;
    }
}
