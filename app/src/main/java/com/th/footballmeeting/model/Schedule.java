package com.th.footballmeeting.model;

/**
 * Created by macbookpro on 9/24/2017 AD.
 */

public class Schedule {
    public String displayTime;
    int id;
    int field_id;
    String date;
    String schedule;
    String status;
    int meeting_id;
    int customer_id;
    int time;
    String created_at;
    String updated_at;

    public Schedule(String time, String status) {
        this.status = status;
        this.displayTime = time;
    }

    public Schedule(String displayTime, int time, String status) {
        this.status = status;
        this.displayTime = displayTime;
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
