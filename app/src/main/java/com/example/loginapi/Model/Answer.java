package com.example.loginapi.Model;

public class Answer {
    private String answer_id, answer_text, question_id, status;

    public Answer() {
    }

    public Answer(String answer_id, String answer_text, String question_id, String status) {
        this.answer_id = answer_id;
        this.answer_text = answer_text;
        this.question_id = question_id;
        this.status = status;
    }

    public String getAnswer_id() {
        return answer_id;
    }

    public void setAnswer_id(String answer_id) {
        this.answer_id = answer_id;
    }

    public String getAnswer_text() {
        return answer_text;
    }

    public void setAnswer_text(String answer_text) {
        this.answer_text = answer_text;
    }

    public String getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(String question_id) {
        this.question_id = question_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
