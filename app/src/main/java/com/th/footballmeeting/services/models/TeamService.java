package com.th.footballmeeting.services.models;

import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.th.footballmeeting.MainApplication;
import com.th.footballmeeting.model.Team;
import com.th.footballmeeting.services.DataService;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by macbookpro on 10/12/2017 AD.
 */

public class TeamService extends DataService {
    public TeamService() {
        super();
    }
    public void create(int customerId, String name, String description){}
    public void addMember(int teamId, int customerId){}
    public void removeMember(int relationId){}
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
