package com.th.footballmeeting.fragment.team_management;

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
import com.th.footballmeeting.adapter.TeamListAdapter;
import com.th.footballmeeting.adapter.TeamRequestAdapter;
import com.th.footballmeeting.model.Customer;
import com.th.footballmeeting.model.Team;
import com.th.footballmeeting.services.models.UserService;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TeamManagementTeamRequest.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TeamManagementTeamRequest#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TeamManagementTeamRequest extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public ArrayList<Team> teams;
    public CustomerActivity activity;
    public UserService service;
    public ListView list;
    public Customer user;

    private OnFragmentInteractionListener mListener;

    public TeamManagementTeamRequest() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment TeamManagementTeamRequest.
     */
    // TODO: Rename and change types and number of parameters
    public static TeamManagementTeamRequest newInstance() {
        TeamManagementTeamRequest fragment = new TeamManagementTeamRequest();
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
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_team_management_team_list, container, false);
        CustomerActivity activity =  (CustomerActivity) getActivity();
        MainApplication app = (MainApplication) activity.getApplication();
        this.user = app.user;
        this.list = (ListView) v.findViewById(R.id.team_list_view);
        this.service = new UserService(new UserService.CallbackList() {
            @Override
            public void callback(boolean status, ArrayList<?> obj) {
                if(status){
                    TeamManagementTeamRequest.this.teams = (ArrayList<Team>)obj;
                    TeamRequestAdapter adapter = new TeamRequestAdapter(getActivity(), TeamManagementTeamRequest.this, (ArrayList<Team>)obj);
                    TeamManagementTeamRequest.this.list.setAdapter(adapter);
                }
            }
        });
        this.service.teamsInvite(this.user.getId());
        return v;
    }

    public void reload(){
        this.service.teamsInvite(this.user.getId());
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
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
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
