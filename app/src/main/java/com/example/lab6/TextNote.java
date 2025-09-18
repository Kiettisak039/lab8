package com.example.lab6;

public class TextNote extends Note{
    //Attribute
    private  String textContent;
    //getter method
    public String getTextContent(){
        return textContent;
    }

    //setter method
    public void setTextContent(String newContent){
        this.textContent = newContent;


    }


    //Method
    public String  getSummary(){
        return title+":"+textContent+"("+createdDate+")";
        //System.out.println(title+":"+content+"("+createdDate+")");
    }
}