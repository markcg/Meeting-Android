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
import com.th.footballmeeting.fragment.field.FieldsFragment;
import com.th.footballmeeting.model.Field;
import com.th.footballmeeting.model.Meeting;
import com.th.footballmeeting.model.Team;
import com.th.footballmeeting.services.models.MeetingService;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MeetingDetail.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MeetingDetail#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MeetingDetail extends Fragment {
    private static final String MEETINGID = "meetingId";
    public int meetingId;
    public Meeting meeting;
    public ArrayList<Team> teams;
    public MeetingService meetingService;
    public MeetingService teamService;
    public MeetingService optimalService;

    public TextView name;
    public TextView date;
    public TextView start;
    public TextView end;
    public ListView list;
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
        this.name = (TextView)v.findViewById(R.id.name);
        this.date = (TextView)v.findViewById(R.id.date);
        this.start = (TextView)v.findViewById(R.id.start);
        this.end = (TextView) v.findViewById(R.id.end);
        this.list = (ListView) v.findViewById(R.id.meeting_team_list);

        this.meetingService = new MeetingService(new MeetingService.Callback() {
            @Override
            public void callback(boolean status, Object obj) {
                if(status){
                    MeetingDetail.this.meeting = (Meeting) obj;
                    MeetingDetail.this.reloadMeetingDetail((Meeting) obj);
                    MeetingDetail.this.teamService.teams(((Meeting) obj).id);
                }
            }
        });
        this.teamService = new MeetingService(new MeetingService.CallbackList() {
            @Override
            public void callback(boolean status, ArrayList<?> obj) {
                if(status){
                    MeetingDetail.this.teams = (ArrayList<Team>)obj;
                    int id = MeetingDetail.this.meeting.id;
                    MeetingTeamListAdapter adapter = new MeetingTeamListAdapter(getActivity(), MeetingDetail.this, (ArrayList<Team>)obj, id);
                    list.setAdapter(adapter);
                }
            }
        });
        this.optimalService = new MeetingService(new MeetingService.CallbackList() {
            @Override
            public void callback(boolean status, ArrayList<?> obj) {
                if(status){
                    ArrayList<Field> result = (ArrayList<Field>)obj;
                    int id = MeetingDetail.this.meeting.id;
                    activity.addChildFragment(FieldsFragment.newInstance(meeting, result), MeetingDetail.newInstance(meetingId));
                }
            }
        });

        Button button = (Button) v.findViewById(R.id.meeting_invite);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                activity.addChildFragment(MeetingTeamInvite.newInstance(meetingId), MeetingDetail.newInstance(meetingId));
            }
        });

        Button optimal = (Button) v.findViewById(R.id.meeting_optimal);
        optimal.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MeetingDetail.this.optimalService.optimal(meetingId);
            }
        });
        this.meetingService.get(meetingId);
        return v;
    }

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
    /* UI Update */
    public void reloadTeam(int id){
        this.teamService.teams(id);
    }
    public void reloadMeetingDetail(Meeting meeting){
        name.setText(meeting.getName());
        date.setText(meeting.getDate());
        start.setText(meeting.getStart());
        end.setText(meeting.getEnd());
    }
}
