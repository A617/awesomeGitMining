package edu.nju.model;

import java.sql.Date;

public class EmailSta {
    private String domain;

    private int count ;

    private double percent;

    public EmailSta(){


    }

    public EmailSta(String domain, int count, double percent) {
        this.domain = domain;
        this.count = count;
        this.percent = percent;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }
}