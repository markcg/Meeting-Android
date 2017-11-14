package com.th.footballmeeting.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.th.footballmeeting.R;
import com.th.footballmeeting.model.Schedule;

import java.util.ArrayList;

/**
 * Created by macbookpro on 9/24/2017 AD.
 */

public class ScheduleListAdapter extends BaseAdapter {
    private ArrayList<Schedule> reserves;
    private static LayoutInflater inflater = null;

    public ScheduleListAdapter(Activity activity, ArrayList<Schedule> reserves) {
        this.reserves = reserves;
        inflater = (LayoutInflater) activity.getLayoutInflater();
    }

    @Override
    public int getCount() {
        return this.reserves.size();
    }

    @Override
    public Object getItem(int position) {
        return this.reserves.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if (convertView == null)
            vi = inflater.inflate(R.layout.list_schedule, parent, false);
        Schedule reserve = (Schedule) getItem(position);
        TextView name = (TextView) vi.findViewById(R.id.time);
        name.setText(reserve.getTime());

        String status = reserve.getStatus();
        Button button = (Button) vi.findViewById(R.id.reserve);
        button.setText(status);
        if(status.equals("Available")){
            button.setBackgroundColor(Color.GREEN);
        } else {
            button.setBackgroundColor(Color.RED);
        }
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });
        return vi;
    }
}
