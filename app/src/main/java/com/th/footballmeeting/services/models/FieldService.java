package com.th.footballmeeting.services.models;

import com.th.footballmeeting.services.DataService;

/**
 * Created by macbookpro on 10/12/2017 AD.
 */

public class FieldService extends DataService{
    public FieldService() {
        super();
    }
    public void loginField(String username, String password) {
    }
    public void registerField(
            String username,
            String password,
            String name,
            String description,
            String address,
            String email,
            String phone,
            String latitude,
            String longitude) {
    }
    public void approveReserve(int reserveId){}
    public void rejectReserve(int reserveId){}
    public void reserves(int fieldId){}
}
