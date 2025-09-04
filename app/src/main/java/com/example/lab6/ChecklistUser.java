package com.example.lab6;

import java.util.List;

public class ChecklistUser extends User {
    //Attribute
    private List<String> userid;

    //getter method
    private List<String> getUserid() {
        return userid;
    }

    //setter method
    private void setUserid(List<String> newUserid) {
        this.userid = newUserid;
    }
    public String  getSummary(){
        return name+":("+id+")";
    }
}