package com.th.footballmeeting.fragment.meeting;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.th.footballmeeting.MainApplication;
import com.th.footballmeeting.R;
import com.th.footballmeeting.activity.CustomerActivity;
import com.th.footballmeeting.adapter.MeetingInviteAdapter;
import com.th.footballmeeting.adapter.MeetingListAdapter;
import com.th.footballmeeting.model.Customer;
import com.th.footballmeeting.model.Meeting;
import com.th.footballmeeting.services.models.UserService;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MeetingInvite.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MeetingInvite#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MeetingInvite extends Fragment {
    public ArrayList<Meeting> meetings;
    public UserService service;
    public ListView list;
    public Customer user;
    public CustomerActivity activity;
    private OnFragmentInteractionListener mListener;

    public MeetingInvite() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MeetingInvite.
     */
    // TODO: Rename and change types and number of parameters
    public static MeetingInvite newInstance() {
        MeetingInvite fragment = new MeetingInvite();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_meeting_invite, container, false);
        this.activity =  (CustomerActivity) getActivity();

        MainApplication app = (MainApplication) activity.getApplication();
        this.user = app.user;
        this.list = (ListView) v.findViewById(R.id.invite_list);
        this.service = new UserService(new UserService.CallbackList() {
            @Override
            public void callback(boolean status, ArrayList<?> obj) {
                if(status){
                    ArrayList<Meeting> meetings = (ArrayList<Meeting>)obj;
                    Collections.reverse(meetings);
                    MeetingInvite.this.meetings = meetings;
                    MeetingInviteAdapter adapter = new MeetingInviteAdapter(MeetingInvite.this.activity, MeetingInvite.this, meetings);
                    MeetingInvite.this.list.setAdapter(adapter);
                }
            }
        });
        this.service.meetingsInvite(user.getId());
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
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void reload(){
        this.service.meetingsInvite(user.getId());
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
