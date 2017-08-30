package com.th.footballmeeting.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by macbookpro on 7/26/2017 AD.
 */

public class Team implements Parcelable {
    private String name;
    private String description;
    private ArrayList<Member> members;

    public Team() {
        this.members = new ArrayList<Member>();
    }

    public Team(String name, String description, ArrayList<Member> members) {
        this.name = name;
        this.description = description;
        this.members = members;
    }

    public Team(Parcel in) {
        name = in.readString();
        description = in.readString();
        members = in.readArrayList(null);
    }

    public static final Creator<Team> CREATOR = new Creator<Team>() {
        @Override
        public Team createFromParcel(Parcel in) {
            return new Team(in);
        }

        @Override
        public Team[] newArray(int size) {
            return new Team[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Member> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<Member> members) {
        this.members = members;
    }

    public void addMember(Member member) {
        if (this.members == null) {
            this.members = new ArrayList<Member>();
        }
        this.members.add(member);

    }

    public void removeMember(int position) {
        this.members.remove(position);
    }

    public String toString() {
        System.out.println("Name: " + this.name);
        System.out.println("Description: " + this.description);
        return this.name;
    }

    public boolean isExist(String name) {
        for (Member member : this.members) {
            return member.getName().toString() == name;
        }
        return false;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(description);
        dest.writeList(members);
    }
}
