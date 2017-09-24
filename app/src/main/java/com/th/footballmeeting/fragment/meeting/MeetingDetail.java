package com.th.footballmeeting.fragment.meeting;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.th.footballmeeting.activity.CustomerActivity;
import com.th.footballmeeting.activity.MainActivity;
import com.th.footballmeeting.R;
import com.th.footballmeeting.adapter.MeetingTeamListAdapter;
import com.th.footballmeeting.model.Meeting;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MeetingDetail.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MeetingDetail#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MeetingDetail extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String MEETINGID = "meetingId";

    // TODO: Rename and change types of parameters
    private int meetingId;

    private OnFragmentInteractionListener mListener;

    public MeetingDetail() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MeetingDetail.
     */
    // TODO: Rename and change types and number of parameters
    public static MeetingDetail newInstance(int meetingId) {
        MeetingDetail fragment = new MeetingDetail();
        Bundle args = new Bundle();
        args.putInt(MEETINGID, meetingId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            meetingId = getArguments().getInt(MEETINGID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_meeting_detail, container, false);
        final CustomerActivity activity = (CustomerActivity)  getActivity();
        Meeting meeting = (Meeting) activity.getMeeting(this.meetingId);

        TextView name = (TextView)v.findViewById(R.id.name);
        name.setText(meeting.getName());
        TextView date = (TextView)v.findViewById(R.id.date);
        date.setText(meeting.getDate());
        TextView start = (TextView)v.findViewById(R.id.start);
        start.setText(meeting.getStart());
        TextView end = (TextView) v.findViewById(R.id.end);
        end.setText(meeting.getEnd());

        ListView list = (ListView) v.findViewById(R.id.meeting_team_list);
        MeetingTeamListAdapter adapter = new MeetingTeamListAdapter(getActivity(), meeting.getTeams(), meetingId);
        list.setAdapter(adapter);

        Button button = (Button) v.findViewById(R.id.meeting_invite);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                activity.addChildFragment(MeetingTeamInvite.newInstance(meetingId), MeetingDetail.newInstance(meetingId));
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
