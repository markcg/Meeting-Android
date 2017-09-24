package com.th.footballmeeting;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;
import com.th.footballmeeting.model.Customer;
import com.th.footballmeeting.model.Meeting;
import com.th.footballmeeting.model.Member;
import com.th.footballmeeting.model.Team;

import java.util.ArrayList;

/**
 * Created by macbookpro on 9/23/2017 AD.
 */

public class MainApplication extends Application {
    public Customer user;
    public static ArrayList<Team> searchTeam;
    public ArrayList<Meeting> searchMeeting;
    public ArrayList<Member> searchMember;

    public void onCreate(){
        super.onCreate();
        AndroidNetworking.initialize(getApplicationContext());
    }
}
