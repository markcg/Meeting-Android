package com.th.footballmeeting.fragment.team_management;

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

import com.th.footballmeeting.activity.CustomerActivity;
import com.th.footballmeeting.R;
import com.th.footballmeeting.adapter.FriendAddListAdapter;
import com.th.footballmeeting.adapter.MemberInviteListAdapter;
import com.th.footballmeeting.fragment.friend.AddFriendFragment;
import com.th.footballmeeting.model.Customer;
import com.th.footballmeeting.services.models.TeamService;
import com.th.footballmeeting.services.models.UserService;

import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TeamManagementTeamInvite.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TeamManagementTeamInvite#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TeamManagementTeamInvite extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String TEAMID = "teamId";

    // TODO: Rename and change types of parameters
    public int teamId;
    public ArrayList<Customer> members;
    public ListView list;
    public CustomerActivity activity;
    public TeamService service;

    private OnFragmentInteractionListener mListener;

    public TeamManagementTeamInvite() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment TeamManagementTeamInvite.
     */
    // TODO: Rename and change types and number of parameters
    public static TeamManagementTeamInvite newInstance(int teamId) {
        TeamManagementTeamInvite fragment = new TeamManagementTeamInvite();
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
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_team_management_team_invite, container, false);
        this.activity = (CustomerActivity) getActivity();
        this.list = (ListView) v.findViewById(R.id.search_list);
        this.service = new TeamService(new TeamService.CallbackList() {
            @Override
            public void callback(boolean status, ArrayList<?> obj) {
                if(status){
                    TeamManagementTeamInvite.this.members = (ArrayList<Customer>)obj;
                    MemberInviteListAdapter adapter = new MemberInviteListAdapter(getActivity(), TeamManagementTeamInvite.this, (ArrayList<Customer>)obj, TeamManagementTeamInvite.this.teamId);
                    TeamManagementTeamInvite.this.list.setAdapter(adapter);
                }
            }
        });

        final EditText search = (EditText) v.findViewById(R.id.search_input);
        search.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus) {
                    String keyword = search.getText().toString();
                    int id = TeamManagementTeamInvite.this.teamId;

                    if (!isValidText(search.getText().toString())) {
                        alertValidation("Username or name is incorrect format.\n" +
                                "Please use only a-z, A-Z and 0-9");
                        return;
                    } else {
                        TeamManagementTeamInvite.this.service.searchNewMember(keyword, id);
                    }
                }

            }
        });
        this.service.searchNewMember("", teamId);
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
        void onFragmentInteraction(Uri uri);
    }
    /* UI Reload */
    public void reloadMember(){
        service.searchNewMember("", teamId);
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
        Pattern p = Pattern.compile("[^A-Za-z0-9]");
        if (p.matcher(name).find()) {
            return false;
        }
        return true;
    }
}
