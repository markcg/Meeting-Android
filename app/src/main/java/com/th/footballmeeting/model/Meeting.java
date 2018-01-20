package com.th.footballmeeting.model;

import java.util.ArrayList;

/**
 * Created by macbookpro on 7/26/2017 AD.
 */

public class Meeting {
    public int id;
    public String name;
    public String detail;
    public String date;
    public String start;
    public String end;
    public String customer_id;
    public String team_name;
    public ArrayList<Team> teams;

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

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
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

    public void removeTeam(int position) {
        this.teams.remove(position);
    }

    public boolean isExist(String name) {
        for (Team team : this.teams) {
            return team.getName().toString() == name;
        }
        return false;
    }
}
