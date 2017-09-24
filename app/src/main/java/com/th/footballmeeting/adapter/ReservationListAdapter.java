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
import com.th.footballmeeting.fragment.team_management.TeamManagementTeamDetail;
import com.th.footballmeeting.fragment.team_management.TeamManagementTeamList;
import com.th.footballmeeting.model.Request;
import com.th.footballmeeting.model.Team;

import java.util.ArrayList;

/**
 * Created by macbookpro on 9/24/2017 AD.
 */

public class ReservationListAdapter extends BaseAdapter {
    private Activity activity;
    private ArrayList<Request> reservations;
    private static LayoutInflater inflater = null;

    public ReservationListAdapter(Activity activity, ArrayList<Request> reservations) {
        this.activity = activity;
        this.reservations = reservations;
        inflater = (LayoutInflater) activity.getLayoutInflater();
    }

    @Override
    public int getCount() {
        return this.reservations.size();
    }

    @Override
    public Object getItem(int position) {
        return this.reservations.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if (convertView == null)
            vi = inflater.inflate(R.layout.reserve_list, parent, false);
        Request request = (Request) getItem(position);

        TextView by = (TextView) vi.findViewById(R.id.by);
        by.setText(request.getBy());

        TextView date = (TextView) vi.findViewById(R.id.date);
        date.setText(request.getDate());

        Button stats = (Button) vi.findViewById(R.id.stats);
        stats.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });

        Button approve = (Button) vi.findViewById(R.id.approve);
        stats.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });

        Button cancel = (Button) vi.findViewById(R.id.cancel);
        stats.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });

        return vi;
    }
}
