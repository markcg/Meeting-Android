package com.th.footballmeeting.services.models;

import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.th.footballmeeting.model.Customer;
import com.th.footballmeeting.model.Field;
import com.th.footballmeeting.model.Meeting;
import com.th.footballmeeting.model.Schedule;
import com.th.footballmeeting.model.Team;
import com.th.footballmeeting.services.DataService;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by macbookpro on 10/12/2017 AD.
 */

public class MeetingService extends DataService {
    public MeetingService() {
        super();
    }
    public MeetingService(Callback callback) {
        super(callback);
    }
    public MeetingService(CallbackList callback) {
        super(callback);
    }

    public void get(int meetingId){
        AndroidNetworking.get(this.url + "meeting/get")
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
                            Meeting meeting = null;
                            if(status){
                                Gson gson = new Gson();
                                String raw = response.getString("message");
                                meeting = gson.fromJson(raw , Meeting.class);
                            }
                            callback.callback(status, meeting);
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
    public void create(int customerId, String name, String date, String start, String end, String detail){
        AndroidNetworking.get(this.url + "meeting/create")
                .addQueryParameter("customer_id", Integer.toString(customerId))
                .addQueryParameter("name", name)
                .addQueryParameter("date", date)
                .addQueryParameter("start", start)
                .addQueryParameter("end", end)
                .addQueryParameter("detail", detail)
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
                            Meeting meeting = null;
                            if(status){
                                Gson gson = new Gson();
                                String raw = response.getString("message");
                                meeting = gson.fromJson(raw , Meeting.class);
                            }
                            callback.callback(status, meeting);
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
    public void delete(int meetingId){
        AndroidNetworking.get(this.url + "meeting/delete")
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
    /* Team */
    public void addTeam(int meetingId, int teamId){
        AndroidNetworking.get(this.url + "meeting/team/add")
                .addQueryParameter("team_id", Integer.toString(teamId))
                .addQueryParameter("meeting_id", Integer.toString(meetingId))
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
    public void removeTeam(int relationId){
        AndroidNetworking.get(this.url + "meeting/team/delete")
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
    public void confirmTeam(int relationId){
        AndroidNetworking.get(this.url + "meeting/team/confirm")
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
    public void teams(int meetingId){
        AndroidNetworking.get(this.url + "meeting/get/teams")
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
                            ArrayList<Team> teams = null;
                            if(status){
                                Gson gson = new Gson();
                                String raw = response.getString("message");
                                teams = gson.fromJson(raw , new TypeToken<ArrayList<Team>>(){}.getType());
                            }
                            callbackList.callback(status, teams);
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
    public void schedules(int meetingId){
        AndroidNetworking.get(this.url + "meeting/get/schedules")
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
                            ArrayList<Schedule> schedules = null;
                            if(status){
                                Gson gson = new Gson();
                                String raw = response.getString("message");
                                schedules = gson.fromJson(raw , new TypeToken<ArrayList<Schedule>>(){}.getType());
                            }
                            callbackList.callback(status, schedules);
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
    public void optimal(int meetingId){
        AndroidNetworking.get(this.url + "meeting/get/optimize")
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
                            ArrayList<Field> fields = null;
                            if(status){
                                Gson gson = new Gson();
                                String raw = response.getString("message");
                                fields = gson.fromJson(raw , new TypeToken<ArrayList<Field>>(){}.getType());
                            }
                            callbackList.callback(status, fields);
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
    /* Search */
    public void searchNewTeam(String name, int id) {
        AndroidNetworking.get(this.url + "meeting/team/new/search")
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
                            ArrayList<Team> teams = null;
                            if(status){
                                Gson gson = new Gson();
                                String raw = response.getString("message");
                                teams = gson.fromJson(raw , new TypeToken<ArrayList<Team>>(){}.getType());
                            }
                            callbackList.callback(status, teams);
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
