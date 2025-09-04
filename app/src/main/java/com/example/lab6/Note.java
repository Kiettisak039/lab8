package com.example.lab6;

public abstract class Note {
    //Attribute
    public String title;
    public String createdDate;
    public User user;
    //getter method
    public String getTitle(){
        return title;
    }
    public String getCreatedDate(){
        return createdDate;
    }
    public User getUser(){
        return user;
    }
    //setter method
    public void setTitle(String newTitle){
        this.title = newTitle;

    }
    public void setCreatedDate(String newCreatedDate){
        this.createdDate=newCreatedDate;
    }
    public User getName(String newUser){this.user = user;
        return null;
    }
    //Method
    abstract public String  getSummary();

    //System.out.println(title+":"+content+"("+createdDate+")");

}