package com.th.footballmeeting.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.th.footballmeeting.activity.CustomerActivity;
import com.th.footballmeeting.activity.MainActivity;
import com.th.footballmeeting.R;
import com.th.footballmeeting.fragment.team_management.TeamManagementTeamDetail;
import com.th.footballmeeting.model.Customer;
import com.th.footballmeeting.services.DataService;
import com.th.footballmeeting.services.ValidationService;
import com.th.footballmeeting.services.models.TeamService;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by macbookpro on 7/26/2017 AD.
 */

public class MemberListAdapter extends BaseAdapter {
    public int teamId;
    public Activity activity;
    public ArrayList<Customer> members;
    public ValidationService validator;
    public TeamManagementTeamDetail fragment;
    public TeamService service;
    public static LayoutInflater inflater = null;

    public MemberListAdapter(Activity activity, TeamManagementTeamDetail fragment, ArrayList<Customer> members, int teamId) {
        this.activity = activity;
        this.members = members;
        this.teamId = teamId;
        this.fragment = fragment;
        this.validator = new ValidationService((AppCompatActivity) activity);
        this.service = new TeamService(new DataService.Callback() {
            @Override
            public void callback(boolean status, Object obj) {
                if(status){
                    MemberListAdapter.this.fragment.reloadMember(MemberListAdapter.this.teamId);
                }
            }
        });
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
        final Customer member = (Customer) getItem(position);
        TextView name = (TextView) vi.findViewById(R.id.member_name);
        name.setText(member.getName());

        Button attendance = (Button) vi.findViewById(R.id.member_attendance_stat);
        attendance.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(vi.getContext());
                alert.setTitle(member.getName());
                alert.setMessage(generateAttendanceRecord(member));
                alert.setPositiveButton("Close", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alert.show();
            }
        });

        Button competition = (Button) vi.findViewById(R.id.member_com_stat);
        competition.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(vi.getContext());
                alert.setTitle(member.getName());
                alert.setMessage(generateCompetitionRecord(member));
                alert.setPositiveButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alert.show();
            }
        });

        Button button = (Button) vi.findViewById(R.id.member_remove);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(vi.getContext());
                alert.setTitle("Delete");
                alert.setMessage("Are you sure you want to delete?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MemberListAdapter.this.service.removeMember(member.getId());
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

    public String generateAttendanceRecord(Customer member) {
        Random r = new Random();
        int attend = r.nextInt(10 - 0);
        int absent = r.nextInt(10 - 0);
        String attendance = "Attendance Record for " + member.getName();
        attendance += "\nAttend: " + attend;
        attendance += "\nAbsent: " + absent;
        return attendance;
    }

    public String generateCompetitionRecord(Customer member) {
        Random r = new Random();
        int win = r.nextInt(10 - 0);
        int lose = r.nextInt(10 - 0);
        String attendance = "Competition Record for " + member.getName();
        attendance += "\nWin: " + win;
        attendance += "\nLose: " + lose;
        attendance += "\nDraw: " + Math.abs(win - lose);
        return attendance;
    }
}
