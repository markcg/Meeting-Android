package com.th.footballmeeting.adapter;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.th.footballmeeting.activity.CustomerActivity;
import com.th.footballmeeting.activity.MainActivity;
import com.th.footballmeeting.R;
import com.th.footballmeeting.fragment.meeting.MeetingTeamInvite;
import com.th.footballmeeting.model.Team;
import com.th.footballmeeting.services.ValidationService;
import com.th.footballmeeting.services.models.MeetingService;

import java.util.ArrayList;

/**
 * Created by macbookpro on 7/26/2017 AD.
 */

public class TeamInviteListAdapter extends BaseAdapter {
    public int meetingId;
    public Activity activity;
    public ArrayList<Team> teams;
    public MeetingTeamInvite fragment;
    public MeetingService service;
    public ValidationService validator;
    private static LayoutInflater inflater = null;

    public TeamInviteListAdapter(Activity activity, MeetingTeamInvite fragment, ArrayList<Team> teams, int meetingId) {
        this.activity = activity;
        this.teams = teams;
        this.meetingId = meetingId;
        this.fragment = fragment;
        this.validator = new ValidationService((AppCompatActivity) activity);
        this.service = new MeetingService(new MeetingService.Callback() {
            @Override
            public void callback(boolean status, Object obj) {
                if(status){
                    TeamInviteListAdapter.this.validator.successValidation("Team add");
                    TeamInviteListAdapter.this.fragment.reloadTeam();
                    TeamInviteListAdapter.this.fragment.getFragmentManager().popBackStackImmediate();
                } else {
                    TeamInviteListAdapter.this.validator.alertValidation("Team is not exist");
                }
            }
        });
        inflater = (LayoutInflater) activity.getLayoutInflater();
    }

    @Override
    public int getCount() {
        return this.teams.size();
    }

    @Override
    public Object getItem(int position) {
        return this.teams.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if (convertView == null)
            vi = inflater.inflate(R.layout.invite_list, parent, false);
        final Team team = (Team) getItem(position);
        TextView name = (TextView) vi.findViewById(R.id.list_name);
        name.setText(team.getName());

        Button button = (Button) vi.findViewById(R.id.list_invite);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TeamInviteListAdapter.this.service.addTeam(TeamInviteListAdapter.this.meetingId, team.id);
            }
        });
        return vi;
    }
}
