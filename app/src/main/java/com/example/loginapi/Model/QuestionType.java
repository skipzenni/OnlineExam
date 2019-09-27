package com.example.loginapi.Model;

public class QuestionType {
    private String Name;
    private String Image;
    private String Total;

    public QuestionType() {
    }

    public QuestionType(String name, String image, String total) {
        Name = name;
        Image = image;
        Total = total;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getTotal() {
        return Total;
    }

    public void setTotal(String total) {
        Total = total;
    }
}
