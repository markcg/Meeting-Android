package com.th.footballmeeting.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.th.footballmeeting.MainActivity;
import com.th.footballmeeting.R;
import com.th.footballmeeting.model.Member;

import java.util.ArrayList;

/**
 * Created by macbookpro on 7/26/2017 AD.
 */

public class MemberInviteListAdapter extends BaseAdapter {
    private int teamId;
    private Activity activity;
    public ArrayList<Member> members;
    private static LayoutInflater inflater = null;

    public MemberInviteListAdapter(Activity activity, ArrayList<Member> members,int teamId) {
        this.activity = activity;
        this.members = members;
        this.teamId = teamId;
        inflater = (LayoutInflater) activity.getLayoutInflater();
    }

    @Override
    public int getCount() {
        return this.members.size();
    }

    @Override
    public Object getItem(int position) {
        return this.members.get(position);
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
        final Member member = (Member) getItem(position);
        TextView name = (TextView) vi.findViewById(R.id.list_name);
        name.setText(member.getName());

        Button button = (Button) vi.findViewById(R.id.list_invite);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ((MainActivity)inflater.getContext()).addTeamMember(teamId, member);
            }
        });
        return vi;
    }
}
