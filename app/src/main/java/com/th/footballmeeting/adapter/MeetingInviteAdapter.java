package com.th.footballmeeting.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.th.footballmeeting.R;
import com.th.footballmeeting.activity.CustomerActivity;
import com.th.footballmeeting.fragment.meeting.MeetingDetail;
import com.th.footballmeeting.fragment.meeting.MeetingInvite;
import com.th.footballmeeting.fragment.meeting.MeetingList;
import com.th.footballmeeting.model.Meeting;
import com.th.footballmeeting.services.ValidationService;
import com.th.footballmeeting.services.models.MeetingService;

import java.util.ArrayList;

/**
 * Created by macbookpro on 1/21/2018 AD.
 */

public class MeetingInviteAdapter extends BaseAdapter {
    private Activity activity;
    private ArrayList<Meeting> meetings;
    public MeetingInvite fragment;
    public MeetingService service;
    public ValidationService validator;
    private static LayoutInflater inflater = null;

    public MeetingInviteAdapter(Activity activity, MeetingInvite fragment, ArrayList<Meeting> meetings) {
        this.activity = activity;
        this.fragment = fragment;
        this.meetings = meetings;
        this.validator = new ValidationService(activity);
        this.service = new MeetingService(new MeetingService.Callback() {
            @Override
            public void callback(boolean status, Object obj) {
                if(status){
                    MeetingInviteAdapter.this.validator.successValidation("Meeting request accepted");
                    MeetingInviteAdapter.this.fragment.reload();
                }
            }
        });
        inflater = (LayoutInflater) activity.getLayoutInflater();
    }

    @Override
    public int getCount() {
        return this.meetings.size();
    }

    @Override
    public Object getItem(int position) {
        return this.meetings.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if (convertView == null)
            vi = inflater.inflate(R.layout.meeting_invite_list, parent, false);
        final Meeting meeting = (Meeting) getItem(position);
        TextView name = (TextView) vi.findViewById(R.id.search_name);
        name.setText(meeting.name);

        TextView teamName = (TextView) vi.findViewById(R.id.team_name);
        teamName.setText(meeting.team_name);

        Button button = (Button) vi.findViewById(R.id.search_invite);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            MeetingInviteAdapter.this.service.acceptTeam(meeting.id);
            }
        });
        return vi;
    }
}