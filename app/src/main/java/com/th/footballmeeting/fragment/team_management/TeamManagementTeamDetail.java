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

import com.th.footballmeeting.R;
import com.th.footballmeeting.activity.CustomerActivity;
import com.th.footballmeeting.adapter.FriendAddListAdapter;
import com.th.footballmeeting.adapter.MemberListAdapter;
import com.th.footballmeeting.fragment.friend.AddFriendFragment;
import com.th.footballmeeting.model.Customer;
import com.th.footballmeeting.model.Team;
import com.th.footballmeeting.services.DataService;
import com.th.footballmeeting.services.models.TeamService;
import com.th.footballmeeting.services.models.UserService;

import java.util.ArrayList;

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
    public TeamService teamService;
    public TeamService memberService;
    public TextView name;
    public TextView description;
    public ListView list;

    public Team team;
    public ArrayList<Customer> members;

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
        final CustomerActivity activity = (CustomerActivity) getActivity();
        this.teamService = new TeamService(new DataService.Callback() {
            @Override
            public void callback(boolean status, Object obj) {
                if(status){
                    TeamManagementTeamDetail.this.team = (Team) obj;
                    TeamManagementTeamDetail.this.reloadTeamDetail((Team) obj);
                    TeamManagementTeamDetail.this.memberService.members(((Team) obj).id);
                }
            }
        });
        this.memberService = new TeamService(new UserService.CallbackList() {
            @Override
            public void callback(boolean status, ArrayList<?> obj) {
                if(status){
                    TeamManagementTeamDetail.this.members = (ArrayList<Customer>)obj;
                    int id = TeamManagementTeamDetail.this.team.id;
                    MemberListAdapter adapter = new MemberListAdapter(getActivity(), TeamManagementTeamDetail.this, (ArrayList<Customer>)obj, id);
                    list.setAdapter(adapter);
                }
            }
        });

        this.team = activity.getTeam(this.teamId);
        this.name = (TextView) v.findViewById(R.id.team_name);
        this.description = (TextView) v.findViewById(R.id.team_description);
        this.list = (ListView) v.findViewById(R.id.member_list);

        Button button = (Button) v.findViewById(R.id.member_invite);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                activity.addChildFragment(TeamManagementTeamInvite.newInstance(teamId), TeamManagementTeamDetail.newInstance(teamId));
            }
        });

        this.teamService.get(teamId);
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

    /* Update UI */
    public void reloadMember(int id){
        this.memberService.members(id);
    }
    public void reloadTeamDetail(Team team){
        this.name.setText(team.getName());
        this.description.setText(team.getDescription());
    }
}
