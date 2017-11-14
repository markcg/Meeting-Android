package com.th.footballmeeting.services.models;

import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.gson.Gson;
import com.th.footballmeeting.model.Customer;
import com.th.footballmeeting.model.Field;
import com.th.footballmeeting.services.DataService;

import org.json.JSONException;
import org.json.JSONObject;

import static android.content.ContentValues.TAG;

/**
 * Created by macbookpro on 10/12/2017 AD.
 */

public class FieldService extends DataService{
    public FieldService() {
        super();
    }
    public FieldService(Callback callback) {
        super(callback);
    }
    public void login(String username, String password) {
        AndroidNetworking.get(this.url + "field/login")
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
                            Field field = null;
                            if(status){
                                Gson gson = new Gson();
                                String raw = response.getString("message");
                                field = gson.fromJson(raw , Field.class);
                            }
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
    public void register(
            String username,
            String password,
            String name,
            String description,
            String address,
            String email,
            String phone,
            String latitude,
            String longitude) {
        AndroidNetworking.get(this.url + "field/register")
                .addQueryParameter("username", username)
                .addQueryParameter("password", password)
                .addQueryParameter("name", name)
                .addQueryParameter("email", email)
                .addQueryParameter("description", description)
                .addQueryParameter("address", address)
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
                            Field field = null;
                            if(status){
                                Gson gson = new Gson();
                                String raw = response.getString("message");
                                field = gson.fromJson(raw , Field.class);
                            }
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
    public void forgotPassword(String username, String email){
        AndroidNetworking.get(this.url + "field/forget-password")
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
                            Field field = null;
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
    public void approveReserve(int reserveId){}
    public void rejectReserve(int reserveId){}
    public void reserves(int fieldId){}
}
