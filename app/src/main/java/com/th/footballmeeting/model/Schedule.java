package com.th.footballmeeting.model;

/**
 * Created by macbookpro on 9/24/2017 AD.
 */

public class Schedule {
    int field_id;
    String date;
    String schedule;
    String status;
    int meeting_id;
    int customer_id;
    String time;
    String created_at;
    String updated_at;

    public Schedule(String time, String status) {
        this.status = status;
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
