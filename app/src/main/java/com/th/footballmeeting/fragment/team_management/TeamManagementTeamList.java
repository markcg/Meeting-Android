package com.th.footballmeeting.fragment.team_management;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.th.footballmeeting.MainApplication;
import com.th.footballmeeting.activity.CustomerActivity;
import com.th.footballmeeting.activity.MainActivity;
import com.th.footballmeeting.R;
import com.th.footballmeeting.adapter.FriendAddListAdapter;
import com.th.footballmeeting.adapter.TeamListAdapter;
import com.th.footballmeeting.fragment.friend.AddFriendFragment;
import com.th.footballmeeting.model.Customer;
import com.th.footballmeeting.model.Team;
import com.th.footballmeeting.services.models.UserService;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TeamManagementTeamList.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TeamManagementTeamList#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TeamManagementTeamList extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String TEAMS = "teams";

    public ArrayList<Team> teams;
    public CustomerActivity activity;
    public UserService service;
    public ListView list;
    public boolean isOwner;

    private OnFragmentInteractionListener mListener;

    public TeamManagementTeamList() {}

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment TeamManagementTeamList.
     */
    public static TeamManagementTeamList newInstance(boolean isOwner) {
        TeamManagementTeamList fragment = new TeamManagementTeamList();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        fragment.isOwner = isOwner;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            teams = getArguments().getParcelableArrayList(TEAMS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_team_management_team_list, container, false);
        this.activity =  (CustomerActivity) getActivity();
        MainApplication app = (MainApplication) activity.getApplication();
        Customer user = app.user;
        this.list = (ListView) v.findViewById(R.id.team_list_view);
        this.service = new UserService(new UserService.CallbackList() {
            @Override
            public void callback(boolean status, ArrayList<?> obj) {
                if(status){
                    TeamManagementTeamList.this.teams = (ArrayList<Team>)obj;
                    TeamListAdapter adapter = new TeamListAdapter(TeamManagementTeamList.this.activity, (ArrayList<Team>)obj, TeamManagementTeamList.this.isOwner);
                    TeamManagementTeamList.this.list.setAdapter(adapter);
                }
            }
        });
        if(this.isOwner){
            this.service.teams(user.getId());
        } else {
            this.service.teamsMember(user.getId());
        }
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
