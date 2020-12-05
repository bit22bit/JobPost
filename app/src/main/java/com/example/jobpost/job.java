package com.example.jobpost;

import java.io.Serializable;

public class job implements Serializable {

    private  String jobtitle,experience,pay;

    public job(){

    }

    public job(String jobtitle, String experience, String pay) {
        this.jobtitle = jobtitle;
        this.experience = experience;
        this.pay = pay;
    }

    public String getJobtitle() {
        return jobtitle;
    }

    public void setJobtitle(String jobtitle) {
        this.jobtitle = jobtitle;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getPay() {
        return pay;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }
}

