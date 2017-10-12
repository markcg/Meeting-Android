package com.th.footballmeeting.services.models;

import com.th.footballmeeting.services.DataService;

/**
 * Created by macbookpro on 10/12/2017 AD.
 */

public class MeetingService extends DataService {
    public MeetingService() {
        super();
    }
    public void get(int meetingId){}
    public void create(int customerId, String name, String date, String start, String end, String detail){}
    public void addTeam(int meetingId, int teamId){}
    public void removeTeam(int relationId){}
    public void teams(int meetingId){}
}
