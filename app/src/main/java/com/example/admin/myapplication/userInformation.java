package com.example.admin.myapplication;



public class userInformation {

    public String Subject_name;
    public String Information;
    public String Due_date;
    public String Teacher;
    public String Time;

    public userInformation(){

    }

    public userInformation(String subject_name, String information, String due_date, String teacher, String time) {
        Subject_name = subject_name;
        Information = information;
        Due_date = due_date;
        Teacher = teacher;
        Time = time;
    }

}
