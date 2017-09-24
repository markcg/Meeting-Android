package com.th.footballmeeting.model;

/**
 * Created by macbookpro on 9/24/2017 AD.
 */

public class Schedule {
    private String time;
    private String status;

    public Schedule(String time, String status) {
        this.time = time;
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
