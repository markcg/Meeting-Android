package com.th.footballmeeting.model;

/**
 * Created by macbookpro on 9/24/2017 AD.
 */

public class Request {
    private String by;
    private String date;

    public Request(String by, String date) {
        this.by = by;
        this.date = date;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
