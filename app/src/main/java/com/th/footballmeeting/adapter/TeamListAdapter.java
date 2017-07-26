package com.th.footballmeeting.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.th.footballmeeting.MainActivity;
import com.th.footballmeeting.R;
import com.th.footballmeeting.fragment.team_management.TeamManagementTeamDetail;
import com.th.footballmeeting.fragment.team_management.TeamManagementTeamList;
import com.th.footballmeeting.model.Team;

import java.util.ArrayList;

/**
 * Created by macbookpro on 7/26/2017 AD.
 */

public class TeamListAdapter extends BaseAdapter {
    private Activity activity;
    private ArrayList<Team> teams;
    private static LayoutInflater inflater = null;

    public TeamListAdapter(Activity activity, ArrayList<Team> teams) {
        this.activity = activity;
        this.teams = teams;
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
            vi = inflater.inflate(R.layout.team_list, parent, false);
        Team team = (Team) getItem(position);
        Button button = (Button) vi.findViewById(R.id.team_open);
        button.setText(team.getName());
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ((MainActivity)inflater.getContext()).addChildFragment(TeamManagementTeamDetail.newInstance(position), TeamManagementTeamList.newInstance());
            }
        });
        return vi;
    }
}
