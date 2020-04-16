package com.example.interview.mainactivity.Feedback;

public class Feedback_Pojo {

    private String Name;
    private String Email;
    private String Contents;


    public Feedback_Pojo(){

    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getContents() {
        return Contents;
    }

    public void setContents(String contents) {
        Contents = contents;
    }

    public Feedback_Pojo(String name, String email, String contents) {
        Name = name;
        Email = email;
        Contents = contents;


    }
}
