package com.th.footballmeeting.fragment.meeting;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import com.th.footballmeeting.MainActivity;
import com.th.footballmeeting.R;
import com.th.footballmeeting.adapter.MemberInviteListAdapter;
import com.th.footballmeeting.adapter.TeamInviteListAdapter;
import com.th.footballmeeting.model.Member;
import com.th.footballmeeting.model.Team;

import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MeetingTeamInvite.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MeetingTeamInvite#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MeetingTeamInvite extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String MEETINGID = "meetingId";

    // TODO: Rename and change types of parameters
    private int meetingId;

    private OnFragmentInteractionListener mListener;

    public MeetingTeamInvite() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MeetingTeamInvite.
     */
    // TODO: Rename and change types and number of parameters
    public static MeetingTeamInvite newInstance(int meetingId) {
        MeetingTeamInvite fragment = new MeetingTeamInvite();
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
        View v = inflater.inflate(R.layout.fragment_meeting_team_invite, container, false);
        final ListView list = (ListView) v.findViewById(R.id.search_list);

        final MainActivity activity =  (MainActivity) getActivity();
        TeamInviteListAdapter adapter = new TeamInviteListAdapter(activity, activity.getTeams(), meetingId);
        list.setAdapter(adapter);

        final EditText search = (EditText) v.findViewById(R.id.search_input);
        search.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus) {
                    ArrayList<Team> result = activity.searchTeam(search.getText().toString());

                    if (!isValidText(search.getText().toString())) {
                        alertValidation("Username or name is incorrect format.\n" +
                                "Please use only a-z, A-Z and 0-9");
                        return;
                    }

                    if(result.size() <= 0){
                        alertValidation("“Username or name is incorrect”");
                        return;
                    }

                    ((TeamInviteListAdapter) list.getAdapter()).teams = result;
                    ((TeamInviteListAdapter) list.getAdapter()).notifyDataSetChanged();
                }

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

    /* Validation */
    public void alertValidation(String message) {
        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        alert.setTitle("Alert");
        alert.setMessage(message);
        alert.setPositiveButton("Close", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alert.show();
    }

    public boolean isValidText(String name) {
        Pattern p = Pattern.compile("[A-Za-z0-9]");
        if (p.matcher(name).find()) {
            return true;
        }
        return false;
    }
}
