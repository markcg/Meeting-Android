package com.th.footballmeeting.fragment.team_management;

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

import com.th.footballmeeting.MainActivity;
import com.th.footballmeeting.R;
import com.th.footballmeeting.adapter.MemberListAdapter;
import com.th.footballmeeting.adapter.TeamListAdapter;
import com.th.footballmeeting.model.Team;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TeamManagementTeamDetail.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TeamManagementTeamDetail#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TeamManagementTeamDetail extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String TEAMID = "teamId";

    // TODO: Rename and change types of parameters
    public int teamId;
    private Team team;

    private OnFragmentInteractionListener mListener;

    public TeamManagementTeamDetail() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment TeamManagementTeamDetail.
     */
    // TODO: Rename and change types and number of parameters
    public static TeamManagementTeamDetail newInstance(int teamId) {
        TeamManagementTeamDetail fragment = new TeamManagementTeamDetail();
        Bundle args = new Bundle();
        args.putInt(TEAMID, teamId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            teamId = getArguments().getInt(TEAMID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_team_management_team_detail, container, false);
        final MainActivity activity =  (MainActivity) getActivity();

        this.team = activity.getTeam(this.teamId);

        TextView name = (TextView) v.findViewById(R.id.team_name);
        name.setText(this.team.getName());

        TextView description = (TextView) v.findViewById(R.id.team_description);
        description.setText(this.team.getDescription());

        ListView list = (ListView) v.findViewById(R.id.member_list);
        MemberListAdapter adapter = new MemberListAdapter(getActivity(), this.team.getMembers(), this.teamId);
        list.setAdapter(adapter);

        Button button = (Button) v.findViewById(R.id.member_invite);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                activity.addChildFragment(TeamManagementTeamInvite.newInstance(teamId), TeamManagementTeamDetail.newInstance(teamId));
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
