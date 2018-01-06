package com.th.footballmeeting.fragment.reserve;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.th.footballmeeting.MainApplication;
import com.th.footballmeeting.R;
import com.th.footballmeeting.activity.CustomerActivity;
import com.th.footballmeeting.adapter.ScheduleListAdapter;
import com.th.footballmeeting.model.Field;
import com.th.footballmeeting.model.Schedule;
import com.th.footballmeeting.model.ScheduleSearch;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ScheduleFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ScheduleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ScheduleFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    // TODO: Rename and change types of parameters
    private Field field;
    private ArrayList<Schedule> schedules;
    public String date;
    private OnFragmentInteractionListener mListener;

    public ScheduleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ScheduleFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ScheduleFragment newInstance(ScheduleSearch schedules, String date) {
        ScheduleFragment fragment = new ScheduleFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        fragment.schedules = schedules.schedules;
        fragment.field = schedules.field;
        fragment.date = date;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_schedule, container, false);
        MainApplication app = (MainApplication) getActivity().getApplication();
        CustomerActivity activity = (CustomerActivity) getActivity();
        ArrayList<Schedule> schedulesDisplay = new ArrayList<Schedule>();
        for (int i = 0; i < 16; i++) {
            String time = String.format("%02d", i + 8).toString() + ":00";
            String status = "Available";
            if(this.isNotAvailable(i + 8)){
                status = "Not Available";
            }
            schedulesDisplay.add(new Schedule(time, i + 8, status));
        }
        Log.d("Team", schedulesDisplay.toString());

        ListView list = (ListView) v.findViewById(R.id.schedule_list);
        ScheduleListAdapter adapter = new ScheduleListAdapter(getActivity(), schedulesDisplay, this.field, app.user, date);
        list.setAdapter(adapter);

        TextView name = (TextView) v.findViewById(R.id.name);
        name.setText(this.field.name);
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public boolean isNotAvailable(int time) {
        Date date = new Date();
        for (int i = 0; i < this.schedules.size(); i++) {
            Schedule check = this.schedules.get(i);
            if(check.getTime() == time) return true;
        }
        return false;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
