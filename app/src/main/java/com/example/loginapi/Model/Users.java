package com.example.loginapi.Model;

public class Users {
    private String Name;
    private String Pass;

    public Users() {
    }

    public Users(String name, String pass){
        Name = name;
        Pass = pass;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String pass) {
        Pass = pass;
    }
}


