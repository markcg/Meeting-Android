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
import com.th.footballmeeting.model.Field;
import com.th.footballmeeting.model.Friend;
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

public class UserService extends DataService {

    public UserService() {
        super();
    }

    public UserService(Callback callback) {
        super(callback);
    }

    public UserService(CallbackList callback) {
        super(callback);
    }

    public UserService(CallbackWithError callback) {
        super(callback);
    }

    public void login(String username, String password) {
        AndroidNetworking.get(this.url + "customer/login")
                .addQueryParameter("username", username)
                .addQueryParameter("password", password)
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
                            Customer customer = null;
                            if(status){
                                Gson gson = new Gson();
                                String raw = response.getString("message");
                                customer = gson.fromJson(raw , Customer.class);
                            }
                            callback.callback(status, customer);
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

    public void register(
            String username,
            String password,
            String name,
            String email,
            String phone,
            String latitude,
            String longitude) {
        AndroidNetworking.get(this.url + "customer/register")
                .addQueryParameter("username", username)
                .addQueryParameter("password", password)
                .addQueryParameter("name", name)
                .addQueryParameter("email", email)
                .addQueryParameter("phone_number", phone)
                .addQueryParameter("latitude", latitude)
                .addQueryParameter("longitude", longitude)
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
                            String error = response.getString("error");
                            Customer customer = null;
                            if(status){
                                Gson gson = new Gson();
                                String raw = response.getString("message");
                                customer = gson.fromJson(raw , Customer.class);
                            }
                            callbackWithError.callback(status, customer, error);
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
    public void edit(
            int id,
            String name,
            String email,
            String phone) {
        AndroidNetworking.get(this.url + "customer/edit-profile")
                .addQueryParameter("id", Integer.toString(id))
                .addQueryParameter("name", name)
                .addQueryParameter("email", email)
                .addQueryParameter("phone_number", phone)
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
                            Customer customer = null;
                            if(status){
                                Gson gson = new Gson();
                                String raw = response.getString("message");
                                customer = gson.fromJson(raw , Customer.class);
                            }
                            callback.callback(status, customer);
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
    public void changePassword(
            int id,
            String oldPassword,
            String newPassword) {
        AndroidNetworking.get(this.url + "customer/change-password")
                .addQueryParameter("id", Integer.toString(id))
                .addQueryParameter("old_password", oldPassword)
                .addQueryParameter("new_password", newPassword)
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
                            Customer customer = null;
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
    public void forgotPassword(String username, String email){
        AndroidNetworking.get(this.url + "customer/forget-password")
                .addQueryParameter("username", username)
                .addQueryParameter("email", email)
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
                            Customer field = null;
                            callback.callback(status, field);
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
    /* Friend */
    public void addFriend(int friendId, int customerId) {
        AndroidNetworking.get(this.url + "customer/friend/add")
                .addQueryParameter("friend_id", Integer.toString(friendId))
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

    public void acceptFriend(int relationId) {
        AndroidNetworking.get(this.url + "customer/friend/accept")
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

    public void rejectFriend(int relationId) {
        AndroidNetworking.get(this.url + "customer/friend/reject")
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

    public void removeFriend(int relationId) {
        AndroidNetworking.get(this.url + "customer/friend/delete")
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
    /* Relationship */
    public void friends(int customerId) {
        AndroidNetworking.get(this.url + "customer/get/friends")
                .addQueryParameter("id", Integer.toString(customerId))
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
                            ArrayList<Customer> friends = null;
                            if(status){
                                Gson gson = new Gson();
                                String raw = response.getString("message");
                                friends = gson.fromJson(raw , new TypeToken<ArrayList<Customer>>(){}.getType());
                            }
                            callbackList.callback(status, friends);
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
    public void friendsRequest(int customerId) {
        AndroidNetworking.get(this.url + "customer/get/friends-request")
                .addQueryParameter("id", Integer.toString(customerId))
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
                            ArrayList<Customer> friends = null;
                            if(status){
                                Gson gson = new Gson();
                                String raw = response.getString("message");
                                friends = gson.fromJson(raw , new TypeToken<ArrayList<Customer>>(){}.getType());
                            }
                            callbackList.callback(status, friends);
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
    public void teams(int customerId) {
        AndroidNetworking.get(this.url + "customer/get/teams")
                .addQueryParameter("id", Integer.toString(customerId))
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
    public void teamsInvite(int customerId) {
        AndroidNetworking.get(this.url + "customer/get/teams-invite")
                .addQueryParameter("id", Integer.toString(customerId))
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
    public void teamsMember(int customerId) {
        AndroidNetworking.get(this.url + "customer/get/teams-member")
                .addQueryParameter("id", Integer.toString(customerId))
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
    public void meetings(int customerId) {
        AndroidNetworking.get(this.url + "customer/get/meetings")
                .addQueryParameter("id", Integer.toString(customerId))
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
    public void meetingsInvite(int customerId) {
        AndroidNetworking.get(this.url + "customer/get/meetings-invite")
                .addQueryParameter("id", Integer.toString(customerId))
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
    public void search(String name) {
        AndroidNetworking.get(this.url + "customer/search")
                .addQueryParameter("keyword", name)
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
    public void searchNewFriend(String name, int id) {
        AndroidNetworking.get(this.url + "customer/friend/new/search")
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