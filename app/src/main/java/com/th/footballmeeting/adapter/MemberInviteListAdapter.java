package com.th.footballmeeting.adapter;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.th.footballmeeting.activity.CustomerActivity;
import com.th.footballmeeting.R;
import com.th.footballmeeting.fragment.friend.AddFriendFragment;
import com.th.footballmeeting.fragment.team_management.TeamManagementTeamInvite;
import com.th.footballmeeting.model.Customer;
import com.th.footballmeeting.services.ValidationService;
import com.th.footballmeeting.services.models.TeamService;
import com.th.footballmeeting.services.models.UserService;

import java.util.ArrayList;

/**
 * Created by macbookpro on 7/26/2017 AD.
 */

public class MemberInviteListAdapter extends BaseAdapter {
    public int teamId;
    public Activity activity;
    public ArrayList<Customer> members;
    public TeamService service;
    public ValidationService validator;
    public TeamManagementTeamInvite fragment;
    private static LayoutInflater inflater = null;

    public MemberInviteListAdapter(Activity activity, TeamManagementTeamInvite fragment, ArrayList<Customer> members, int teamId) {
        this.activity = activity;
        this.members = members;
        this.teamId = teamId;
        this.fragment = fragment;
        this.validator = new ValidationService((AppCompatActivity) activity);
        this.service = new TeamService(new UserService.Callback() {
            @Override
            public void callback(boolean status, Object obj) {
                if(status){
                    MemberInviteListAdapter.this.validator.successValidation("Member add");
                    MemberInviteListAdapter.this.fragment.reloadMember();
                    MemberInviteListAdapter.this.fragment.getFragmentManager().popBackStackImmediate();
                } else {
                    MemberInviteListAdapter.this.validator.alertValidation("Member is not exist");
                }
            }
        });
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
        final Customer member = (Customer) getItem(position);
        TextView name = (TextView) vi.findViewById(R.id.list_name);
        name.setText(member.getName());

        Button button = (Button) vi.findViewById(R.id.list_invite);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MemberInviteListAdapter.this.service.addMember(MemberInviteListAdapter.this.teamId, member.getId());
            }
        });
        return vi;
    }
}
