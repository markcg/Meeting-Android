package com.th.footballmeeting.fragment.meeting;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.th.footballmeeting.MainActivity;
import com.th.footballmeeting.R;
import com.th.footballmeeting.adapter.MemberListAdapter;
import com.th.footballmeeting.fragment.MeetingFragment;
import com.th.footballmeeting.fragment.team_management.TeamManagementTeamDetail;
import com.th.footballmeeting.fragment.team_management.TeamManagementTeamInvite;
import com.th.footballmeeting.model.Meeting;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MeetingCreate.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MeetingCreate#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MeetingCreate extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public MeetingCreate() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MeetingCreate.
     */
    // TODO: Rename and change types and number of parameters
    public static MeetingCreate newInstance() {
        MeetingCreate fragment = new MeetingCreate();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_meeting_create, container, false);
        final MainActivity activity =  (MainActivity) getActivity();
        final EditText name = (EditText) v.findViewById(R.id.meeting_name_input);
        final EditText date = (EditText) v.findViewById(R.id.meeting_date);
        final EditText start = (EditText) v.findViewById(R.id.meeting_time_start);
        final EditText end = (EditText) v.findViewById(R.id.meeting_time_end);

        Button confirm = (Button) v.findViewById(R.id.create_meeting_done);
        confirm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Meeting meeting = new Meeting(name.getText().toString(), date.getText().toString(), start.getText().toString(), end.getText().toString());
                activity.addMeeting(meeting);
                activity.addChildFragment(MeetingList.newInstance());
            }
        });

        Button cancel = (Button) v.findViewById(R.id.create_meeting_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                activity.addChildFragment(MeetingList.newInstance());
            }
        });
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
