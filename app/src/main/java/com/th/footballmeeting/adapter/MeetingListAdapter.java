package com.th.footballmeeting.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.vision.text.Text;
import com.th.footballmeeting.activity.CustomerActivity;
import com.th.footballmeeting.activity.MainActivity;
import com.th.footballmeeting.R;
import com.th.footballmeeting.fragment.meeting.MeetingDetail;
import com.th.footballmeeting.fragment.meeting.MeetingList;
import com.th.footballmeeting.model.Meeting;

import java.util.ArrayList;

/**
 * Created by macbookpro on 7/26/2017 AD.
 */

public class MeetingListAdapter extends BaseAdapter {
    private Activity activity;
    private ArrayList<Meeting> meetings;
    private static LayoutInflater inflater = null;

    public MeetingListAdapter(Activity activity, ArrayList<Meeting> meetings) {
        this.activity = activity;
        this.meetings = meetings;
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
            vi = inflater.inflate(R.layout.team_list, parent, false);
        final Meeting meeting = (Meeting) getItem(position);
        Button button = (Button) vi.findViewById(R.id.team_open);
        button.setText(meeting.getName());
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ((CustomerActivity)inflater.getContext()).addChildFragment(MeetingDetail.newInstance(meeting.id), MeetingList.newInstance());
            }
        });
        return vi;
    }
}
