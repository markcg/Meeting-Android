package com.th.footballmeeting.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.th.footballmeeting.activity.CustomerActivity;
import com.th.footballmeeting.activity.MainActivity;
import com.th.footballmeeting.R;
import com.th.footballmeeting.fragment.meeting.MeetingDetail;
import com.th.footballmeeting.model.Team;
import com.th.footballmeeting.services.DataService;
import com.th.footballmeeting.services.models.MeetingService;

import java.util.ArrayList;

/**
 * Created by macbookpro on 7/26/2017 AD.
 */

public class MeetingTeamListAdapter extends BaseAdapter {
    public int meetingId;
    public Activity activity;
    public ArrayList<Team> teams;
    public MeetingDetail fragment;
    public MeetingService service;
    private static LayoutInflater inflater = null;

    public MeetingTeamListAdapter(Activity activity, MeetingDetail fragment, ArrayList<Team> teams, int meetingId) {
        this.activity = activity;
        this.teams = teams;
        this.meetingId = meetingId;
        this.fragment = fragment;
        this.service = new MeetingService(new DataService.Callback() {
            @Override
            public void callback(boolean status, Object obj) {
                if(status){
                    MeetingTeamListAdapter.this.fragment.reloadTeam(MeetingTeamListAdapter.this.meetingId);
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
        final View vi = inflater.inflate(R.layout.meeting_team_list, parent, false);
//        if (convertView == null)

        final Team team = (Team) getItem(position);
        TextView name = (TextView) vi.findViewById(R.id.team_name);
        name.setText(team.getName());

        Button button = (Button) vi.findViewById(R.id.team_remove);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(vi.getContext());
                alert.setTitle("Delete");
                alert.setMessage("Are you sure you want to delete?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MeetingTeamListAdapter.this.service.removeTeam(team.id);
                        dialog.dismiss();
                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alert.show();
            }
        });
        return vi;
    }
}
