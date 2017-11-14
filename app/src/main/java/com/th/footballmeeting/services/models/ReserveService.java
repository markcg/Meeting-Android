package com.th.footballmeeting.services.models;

import com.th.footballmeeting.services.DataService;

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
    public void check(String keyword, String date){}
    public void reserve(String field_id, String date, String time){}
}
