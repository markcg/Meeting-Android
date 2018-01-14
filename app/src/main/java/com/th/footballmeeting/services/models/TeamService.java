package com.th.footballmeeting.services.models;

import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.th.footballmeeting.MainApplication;
import com.th.footballmeeting.model.Customer;
import com.th.footballmeeting.model.Meeting;
import com.th.footballmeeting.model.Team;
import com.th.footballmeeting.services.DataService;

import org.json.JSONException;
import org.json.JSONObject;

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
    public TeamService(Callback callback) {
        super(callback);
    }
    public TeamService(CallbackList callback) {
        super(callback);
    }
    public void get(int id){
        AndroidNetworking.get(this.url + "team/get")
                .addQueryParameter("id", Integer.toString(id))
                .setTag(this)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        Log.d(TAG, response.toString());

                        try {
                            boolean status = response.getBoolean("status");
                            Team team = null;
                            if(status){
                                Gson gson = new Gson();
                                String raw = response.getString("message");
                                team = gson.fromJson(raw , Team.class);
                            }
                            callback.callback(status, team);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        // handle error
                        Log.d(TAG, anError.toString());
                    }
                });
    }
    public void create(int customerId, String name, String description){
        AndroidNetworking.get(this.url + "team/create")
                .addQueryParameter("id", Integer.toString(customerId))
                .addQueryParameter("name", name)
                .addQueryParameter("description", description)
                .setTag(this)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        Log.d(TAG, response.toString());

                        try {
                            boolean status = response.getBoolean("status");
                            Team team = null;
                            if(status){
                                Gson gson = new Gson();
                                String raw = response.getString("message");
                                team = gson.fromJson(raw , Team.class);
                            }
                            callback.callback(status, team);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        // handle error
                        Log.d(TAG, anError.toString());
                    }
                });
    }
    public void delete(int teamId){
        AndroidNetworking.get(this.url + "team/delete")
                .addQueryParameter("id", Integer.toString(teamId))
                .setTag(this)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        Log.d(TAG, response.toString());

                        try {
                            boolean status = response.getBoolean("status");
                            callback.callback(status, null);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        // handle error
                        Log.d(TAG, anError.toString());
                    }
                });
    }
    /* Member */
    public void addMember(int teamId, int customerId){
        AndroidNetworking.get(this.url + "team/member/add")
                .addQueryParameter("team_id", Integer.toString(teamId))
                .addQueryParameter("customer_id", Integer.toString(customerId))
                .setTag(this)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        Log.d(TAG, response.toString());

                        try {
                            boolean status = response.getBoolean("status");
                            callback.callback(status, null);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        // handle error
                        Log.d(TAG, anError.toString());
                    }
                });
    }
    public void removeMember(int relationId){
        AndroidNetworking.get(this.url + "team/member/delete")
                .addQueryParameter("id", Integer.toString(relationId))
                .setTag(this)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        Log.d(TAG, response.toString());

                        try {
                            boolean status = response.getBoolean("status");
                            callback.callback(status, null);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        // handle error
                        Log.d(TAG, anError.toString());
                    }
                });
    }
    public void confirmMember(int relationId){
        AndroidNetworking.get(this.url + "team/member/confirm")
                .addQueryParameter("id", Integer.toString(relationId))
                .setTag(this)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        Log.d(TAG, response.toString());

                        try {
                            boolean status = response.getBoolean("status");
                            callback.callback(status, null);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        // handle error
                        Log.d(TAG, anError.toString());
                    }
                });
    }
    /* Collection */
    public void members(int teamId){
        AndroidNetworking.get(this.url + "team/get/members")
                .addQueryParameter("id", Integer.toString(teamId))
                .setTag(this)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        Log.d(TAG, response.toString());

                        try {
                            boolean status = response.getBoolean("status");
                            ArrayList<Customer> members = null;
                            if(status){
                                Gson gson = new Gson();
                                String raw = response.getString("message");
                                members = gson.fromJson(raw , new TypeToken<ArrayList<Customer>>(){}.getType());
                            }
                            callbackList.callback(status, members);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        // handle error
                        Log.d(TAG, anError.toString());
                    }
                });
    }
    public void meetings(int meetingId){
        AndroidNetworking.get(this.url + "team/get/meetings")
                .addQueryParameter("id", Integer.toString(meetingId))
                .setTag(this)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        Log.d(TAG, response.toString());

                        try {
                            boolean status = response.getBoolean("status");
                            ArrayList<Meeting> meetings = null;
                            if(status){
                                Gson gson = new Gson();
                                String raw = response.getString("message");
                                meetings = gson.fromJson(raw , new TypeToken<ArrayList<Meeting>>(){}.getType());
                            }
                            callbackList.callback(status, meetings);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        // handle error
                        Log.d(TAG, anError.toString());
                    }
                });
    }

    /* Other */
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
    public void searchNewMember(String name, int id) {
        AndroidNetworking.get(this.url + "team/member/new/search")
                .addQueryParameter("keyword", name)
                .addQueryParameter("id", Integer.toString(id))
                .setTag(this)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        Log.d(TAG, response.toString());

                        try {
                            boolean status = response.getBoolean("status");
                            ArrayList<Customer> customer = null;
                            if(status){
                                Gson gson = new Gson();
                                String raw = response.getString("message");
                                customer = gson.fromJson(raw , new TypeToken<ArrayList<Customer>>(){}.getType());
                            }
                            callbackList.callback(status, customer);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        // handle error
                        Log.d(TAG, anError.toString());
                    }
                });
    }
}
