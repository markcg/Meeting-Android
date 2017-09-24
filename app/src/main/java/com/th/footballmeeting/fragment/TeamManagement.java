package com.th.footballmeeting.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.th.footballmeeting.activity.CustomerActivity;
import com.th.footballmeeting.activity.MainActivity;
import com.th.footballmeeting.R;
import com.th.footballmeeting.fragment.team_management.TeamManagementCreateTeam;
import com.th.footballmeeting.fragment.team_management.TeamManagementTeamList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TeamManagement.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TeamManagement#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TeamManagement extends Fragment {
    // TODO: Rename parameter arguments, choose names that match

    private OnFragmentInteractionListener mListener;

    public TeamManagement() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment TeamManagement.
     */
    // TODO: Rename and change types and number of parameters
    public static TeamManagement newInstance() {
        TeamManagement fragment = new TeamManagement();
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
        View v = inflater.inflate(R.layout.fragment_team_management, container, false);

        Button createButton = (Button) v.findViewById(R.id.team_management_create);
        createButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ((CustomerActivity) getActivity()).addChildFragment(TeamManagementCreateTeam.newInstance(), TeamManagement.newInstance());
            }
        });

        Button detailButton = (Button) v.findViewById(R.id.team_management_myteam);
        detailButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ((CustomerActivity) getActivity()).addChildFragment(TeamManagementTeamList.newInstance(), TeamManagement.newInstance());
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
