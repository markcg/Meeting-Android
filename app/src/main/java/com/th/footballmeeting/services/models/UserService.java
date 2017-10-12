package com.th.footballmeeting.services.models;

import com.th.footballmeeting.services.DataService;

/**
 * Created by macbookpro on 10/12/2017 AD.
 */

public class UserService extends DataService{
    public UserService() {
        super();
    }
    public void login(String username, String password) {
    }

    public void register(
            String username,
            String password,
            String name,
            String email,
            String phone,
            String latitude,
            String longitude) {

    }
    public void addFriend(int friendId, int customerId){}
    public void acceptFriend(int relationId){}
    public void rejectFriend(int relationId){}
    public void removeFriend(int relationId){}
    public void friends(int customerId){}
}
