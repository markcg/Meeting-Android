package com.th.footballmeeting.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.th.footballmeeting.MainApplication;
import com.th.footballmeeting.R;
import com.th.footballmeeting.activity.CustomerActivity;
import com.th.footballmeeting.activity.CustomerLoginActivity;
import com.th.footballmeeting.fragment.friend.AddFriendFragment;
import com.th.footballmeeting.fragment.friend.RemoveFriendFragment;
import com.th.footballmeeting.model.Customer;
import com.th.footballmeeting.services.ValidationService;
import com.th.footballmeeting.services.models.UserService;

import java.util.ArrayList;

/**
 * Created by macbookpro on 9/24/2017 AD.
 */

public class FriendAddListAdapter extends BaseAdapter {
    public ArrayList<Customer> friends;
    private static LayoutInflater inflater = null;
    public static int customerId;
    public ValidationService validator;
    public UserService service;
    public RemoveFriendFragment fragment;

    public FriendAddListAdapter(Activity activity, AddFriendFragment fragment, ArrayList<Customer> friends, int customerId) {
        this.friends = friends;
        this.customerId = customerId;
        this.validator = new ValidationService((AppCompatActivity) activity);
        this.service = new UserService(new UserService.Callback() {
            @Override
            public void callback(boolean status, Object obj) {
                if(status){
                    FriendAddListAdapter.this.validator.successValidation("Friend request sent");
                    FriendAddListAdapter.this.fragment.reloadFriend();
                } else {
                    FriendAddListAdapter.this.validator.alertValidation("Friend is not exist");
                }
            }
        });
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
        final Customer customer = (Customer) getItem(position);
        TextView name = (TextView) vi.findViewById(R.id.name);
        name.setText(customer.name);

        Button button = (Button) vi.findViewById(R.id.add);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FriendAddListAdapter.this.service.addFriend(customer.getId(), customerId);
            }
        });
        return vi;
    }
}
