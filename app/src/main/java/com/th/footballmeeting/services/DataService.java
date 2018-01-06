package com.th.footballmeeting.services;

import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
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
    public Callback callback;
    public CallbackList callbackList;
    public CallbackBoolean callbackBool;

    public interface Callback{
        public void callback(boolean status, Object obj);
    }

    public interface CallbackList{
        public void callback(boolean status, ArrayList<?> obj);
    }

    public interface CallbackBoolean{
        public void callback(boolean status);
    }

    public DataService() {
        this.url = "http://10.0.2.2/meeting/public/api/";
    }

    public DataService(Callback callback) {
        this();
        this.callback = callback;
    }

    public DataService(CallbackList callback) {
        this();
        this.callbackList = callback;
    }

    public DataService(CallbackBoolean callback) {
        this();
        this.callbackBool = callback;
    }
}
