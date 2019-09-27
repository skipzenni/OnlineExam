package com.example.loginapi.Model;

public class Questions {
    private String Answer_A, Answer_B, Answer_C, Answer_D, CategoriId,CurrentAnswer,ImageQuestion, Question;

    public Questions() {
    }

    public Questions(String answer_A, String answer_B, String answer_C, String answer_D, String categoriId, String currentAnswer, String imageQuestion, String question) {
        Answer_A = answer_A;
        Answer_B = answer_B;
        Answer_C = answer_C;
        Answer_D = answer_D;
        CategoriId = categoriId;
        CurrentAnswer = currentAnswer;
        ImageQuestion = imageQuestion;
        Question = question;
    }

    public String getAnswer_A() {
        return Answer_A;
    }

    public void setAnswer_A(String answer_A) {
        Answer_A = answer_A;
    }

    public String getAnswer_B() {
        return Answer_B;
    }

    public void setAnswer_B(String answer_B) {
        Answer_B = answer_B;
    }

    public String getAnswer_C() {
        return Answer_C;
    }

    public void setAnswer_C(String answer_C) {
        Answer_C = answer_C;
    }

    public String getAnswer_D() {
        return Answer_D;
    }

    public void setAnswer_D(String answer_D) {
        Answer_D = answer_D;
    }

    public String getCategoriId() {
        return CategoriId;
    }

    public void setCategoriId(String categoriId) {
        CategoriId = categoriId;
    }

    public String getCurrentAnswer() {
        return CurrentAnswer;
    }

    public void setCurrentAnswer(String currentAnswer) {
        CurrentAnswer = currentAnswer;
    }

    public String getImageQuestion() {
        return ImageQuestion;
    }

    public void setImageQuestion(String imageQuestion) {
        ImageQuestion = imageQuestion;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }
}
