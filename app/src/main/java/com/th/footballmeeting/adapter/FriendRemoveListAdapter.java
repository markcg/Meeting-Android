package com.th.footballmeeting.adapter;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.th.footballmeeting.R;
import com.th.footballmeeting.activity.CustomerActivity;
import com.th.footballmeeting.fragment.friend.RemoveFriendFragment;
import com.th.footballmeeting.fragment.meeting.MeetingDetail;
import com.th.footballmeeting.fragment.meeting.MeetingList;
import com.th.footballmeeting.model.Meeting;
import com.th.footballmeeting.model.Customer;
import com.th.footballmeeting.services.ValidationService;
import com.th.footballmeeting.services.models.UserService;

import java.util.ArrayList;

/**
 * Created by macbookpro on 9/24/2017 AD.
 */

public class FriendRemoveListAdapter extends BaseAdapter {
    public ArrayList<Customer> friends;
    private static LayoutInflater inflater = null;
    public UserService service;
    public ValidationService validator;
    public RemoveFriendFragment fragment;

    public FriendRemoveListAdapter(Activity activity, RemoveFriendFragment fragment, ArrayList<Customer> friends) {
        this.friends = friends;
        this.fragment = fragment;
        this.validator = new ValidationService((AppCompatActivity) activity);
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
            vi = inflater.inflate(R.layout.remove_friend_list, parent, false);
        final Customer member = (Customer) getItem(position);
        TextView name = (TextView) vi.findViewById(R.id.name);
        name.setText(member.getName());

        service = new UserService(new UserService.Callback() {
            @Override
            public void callback(boolean status, Object obj) {
                if(status){
                    FriendRemoveListAdapter.this.validator.successValidation("Friend removed");
                    FriendRemoveListAdapter.this.fragment.reloadFriend();
                } else {
                    FriendRemoveListAdapter.this.validator.alertValidation("Friend is not exist");
                }
            }
        });

        Button button = (Button) vi.findViewById(R.id.remove);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FriendRemoveListAdapter.this.service.removeFriend(member.getId());
            }
        });
        return vi;
    }
}
