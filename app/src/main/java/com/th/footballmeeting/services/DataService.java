package com.th.footballmeeting.services;

import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.androidnetworking.common.Priority;
import com.th.footballmeeting.MainApplication;
import com.th.footballmeeting.model.Team;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by macbookpro on 9/23/2017 AD.
 */

public class DataService {
    public String url;
    public DataService(){
        this.url = "http://10.0.2.2/meeting/public/api/";
    }
    public void searchTeam(String teamName) {
        AndroidNetworking.get(this.url + "search/teams")
                .addQueryParameter("keyword", teamName)
                .setTag(this)
                .setPriority(Priority.LOW)
                .build()
                .getAsObjectList(Team.class, new ParsedRequestListener<List<Team>>() {
                    @Override
                    public void onResponse(List<Team> teams) {
                        // do anything with response
                        Log.d(TAG, "TeamList size : " + teams.size());
                        for (Team team : teams) {
                            Log.d(TAG, "team");
                        }
                        MainApplication.searchTeam = (ArrayList<Team>) teams;
                    }

                    @Override
                    public void onError(ANError anError) {
                        // handle error
                    }
                });
    }
}
