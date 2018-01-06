package com.th.footballmeeting.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.th.footballmeeting.R;
import com.th.footballmeeting.model.Customer;
import com.th.footballmeeting.model.Field;
import com.th.footballmeeting.model.Schedule;
import com.th.footballmeeting.services.ValidationService;
import com.th.footballmeeting.services.models.FieldService;
import com.th.footballmeeting.services.models.ReserveService;
import com.th.footballmeeting.services.models.UserService;

import java.util.ArrayList;

/**
 * Created by macbookpro on 9/24/2017 AD.
 */

public class ScheduleListAdapter extends BaseAdapter {
    private ArrayList<Schedule> reserves;
    public Field field;
    public Customer user;
    public String date;
    public ReserveService service;
    public ValidationService validator;
    private static LayoutInflater inflater = null;

    public ScheduleListAdapter(Activity activity, ArrayList<Schedule> reserves, Field field, Customer user, String date) {
        this.reserves = reserves;
        this.field = field;
        this.user = user;
        this.date = date;
        this.validator = new ValidationService((AppCompatActivity) activity);
        this.service = new ReserveService(new ReserveService.Callback() {
            @Override
            public void callback(boolean status, Object obj) {
                if(status){
                    ScheduleListAdapter.this.validator.successValidation("Schedule reserved sent");
                }
            }
        });
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
        final Schedule reserve = (Schedule) getItem(position);
        TextView name = (TextView) vi.findViewById(R.id.time);
        name.setText(reserve.displayTime);

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
                int fieldId = ScheduleListAdapter.this.field.id;
                int userId = ScheduleListAdapter.this.user.getId();
                ScheduleListAdapter.this.service.reserveByUser(fieldId, date, reserve.getTime(), userId);
            }
        });
        return vi;
    }
}
