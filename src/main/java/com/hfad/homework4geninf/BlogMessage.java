package com.hfad.homework4geninf;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BlogMessage implements Serializable {


    public BlogMessage(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    private String generateDateString(){
        String PATTERN="dd-MM-yyyy";
        SimpleDateFormat dateFormat=new SimpleDateFormat();
        dateFormat.applyPattern(PATTERN);
        return dateFormat.format(Calendar.getInstance().getTime());
    }
    public BlogMessage(String id, String title, String content, String userLogin, String userId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.userLogin = userLogin;
        this.userId = userId;
        this.date = generateDateString();
    }

    private String id;
    private String title;
    private String content;
    private String userLogin;
    private String userId;
    private String date;


}
