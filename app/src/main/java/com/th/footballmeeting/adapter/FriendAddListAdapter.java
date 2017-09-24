package com.th.footballmeeting.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.th.footballmeeting.R;
import com.th.footballmeeting.model.Member;

import java.util.ArrayList;

/**
 * Created by macbookpro on 9/24/2017 AD.
 */

public class FriendAddListAdapter extends BaseAdapter {
    public ArrayList<Member> friends;
    private static LayoutInflater inflater = null;

    public FriendAddListAdapter(Activity activity, ArrayList<Member> friends) {
        this.friends = friends;
        inflater = (LayoutInflater) activity.getLayoutInflater();
    }

    @Override
    public int getCount() {
        return this.friends.size();
    }

    @Override
    public Object getItem(int position) {
        return this.friends.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if (convertView == null)
            vi = inflater.inflate(R.layout.add_friend_list, parent, false);
        Member member = (Member) getItem(position);
        TextView name = (TextView) vi.findViewById(R.id.name);
        name.setText(member.getName());

        Button button = (Button) vi.findViewById(R.id.add);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });
        return vi;
    }
}
