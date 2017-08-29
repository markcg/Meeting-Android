package com.th.footballmeeting.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.th.footballmeeting.MainActivity;
import com.th.footballmeeting.R;
import com.th.footballmeeting.fragment.team_management.TeamManagementTeamDetail;
import com.th.footballmeeting.fragment.team_management.TeamManagementTeamList;
import com.th.footballmeeting.model.Member;
import com.th.footballmeeting.model.Team;

import java.util.ArrayList;

/**
 * Created by macbookpro on 7/26/2017 AD.
 */

public class MemberListAdapter extends BaseAdapter {
    private int teamId;
    private Activity activity;
    public ArrayList<Member> members;
    private static LayoutInflater inflater = null;

    public MemberListAdapter(Activity activity, ArrayList<Member> members, int teamId) {
        this.activity = activity;
        this.members = members;
        this.teamId = teamId;
        inflater = (LayoutInflater) activity.getLayoutInflater();
    }

    @Override
    public int getCount() {
        if (this.members != null) {
            return this.members.size();
        } else {
            return 0;
        }
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
        final View vi = inflater.inflate(R.layout.member_list, parent, false);
        ;
//        if (convertView == null)
//            vi =
        Member member = (Member) getItem(position);
        TextView name = (TextView) vi.findViewById(R.id.member_name);
        name.setText(member.getName());

        Button button = (Button) vi.findViewById(R.id.member_remove);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(vi.getContext());
                alert.setTitle("Delete");
                alert.setMessage("Are you sure you want to delete?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        members.remove(position);
                        ((MainActivity) inflater.getContext()).removeTeamMember(teamId, position);
                        notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });

                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                alert.show();


            }
        });
        return vi;
    }
}
