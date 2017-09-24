package com.th.footballmeeting.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.th.footballmeeting.activity.CustomerActivity;
import com.th.footballmeeting.activity.MainActivity;
import com.th.footballmeeting.R;
import com.th.footballmeeting.model.Team;

import java.util.ArrayList;

/**
 * Created by macbookpro on 7/26/2017 AD.
 */

public class TeamInviteListAdapter extends BaseAdapter {
    private int meetingId;
    private Activity activity;
    public ArrayList<Team> teams;
    private static LayoutInflater inflater = null;

    public TeamInviteListAdapter(Activity activity, ArrayList<Team> teams, int meetingId) {
        this.activity = activity;
        this.teams = teams;
        this.meetingId = meetingId;
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
                ((CustomerActivity)inflater.getContext()).addMeetingTeam(meetingId, team);
                ((CustomerActivity)inflater.getContext()).onBackPressed();
            }
        });
        return vi;
    }
}
