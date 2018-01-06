package com.th.footballmeeting.services.models;

import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.th.footballmeeting.model.Field;
import com.th.footballmeeting.model.Schedule;
import com.th.footballmeeting.model.ScheduleSearch;
import com.th.footballmeeting.services.DataService;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by macbookpro on 10/12/2017 AD.
 */

public class ReserveService extends DataService{
    public ReserveService() {
        super();
    }
    public ReserveService(Callback callback) {
        super(callback);
    }
    public ReserveService(CallbackBoolean callback) {
        super(callback);
    }

    public void check(String keyword, String date){}
    public void reserve(String field_id, String date, String time){}
    public void reserveByUser(int fieldId, String date, int time, int userId){
        AndroidNetworking.get(this.url + "field/schedule/reserve-by-user")
                .addQueryParameter("field_id", Integer.toString(fieldId))
                .addQueryParameter("date", date)
                .addQueryParameter("time", Integer.toString(time))
                .addQueryParameter("customer_id", Integer.toString(userId))
                .addQueryParameter("status", Integer.toString(0))
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
                        Log.d(TAG, anError.getErrorBody().toString());
                    }
                });
    }
    public void reserveByMeeting(String field_id, String date, String time, int meetingId){}
}
