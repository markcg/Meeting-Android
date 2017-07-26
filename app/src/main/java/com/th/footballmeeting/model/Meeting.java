package com.th.footballmeeting.model;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by macbookpro on 7/26/2017 AD.
 */

public class Meeting {
    private String name;
    private String date;
    private String start;
    private String end;
    private ArrayList<Team> teams;

    public Meeting() {
    }

    public Meeting(String name, String date, String start, String end) {
        this.name = name;
        this.date = date;
        this.start = start;
        this.end = end;
        this.teams = new ArrayList<Team>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public ArrayList<Team> getTeams() {
        return teams;
    }

    public void setTeams(ArrayList<Team> teams) {
        this.teams = teams;
    }

    public void addTeam(Team team) {
        this.teams.add(team);
    }
}
