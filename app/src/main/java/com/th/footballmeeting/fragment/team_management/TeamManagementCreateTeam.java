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
import android.widget.Button;
import android.widget.EditText;

import com.th.footballmeeting.MainApplication;
import com.th.footballmeeting.activity.CustomerActivity;
import com.th.footballmeeting.activity.MainActivity;
import com.th.footballmeeting.R;
import com.th.footballmeeting.model.Customer;
import com.th.footballmeeting.model.Team;
import com.th.footballmeeting.services.DataService;
import com.th.footballmeeting.services.models.TeamService;

import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TeamManagementCreateTeam.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TeamManagementCreateTeam#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TeamManagementCreateTeam extends Fragment {
    // TODO: Rename and change types of parameters
    public EditText name;
    public EditText description;
    public TeamService service;
    public Customer user;

    private OnFragmentInteractionListener mListener;

    public TeamManagementCreateTeam() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment TeamManagementCreateTeam.
     */
    // TODO: Rename and change types and number of parameters
    public static TeamManagementCreateTeam newInstance() {
        TeamManagementCreateTeam fragment = new TeamManagementCreateTeam();
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
        View v = inflater.inflate(R.layout.fragment_team_management_create_team, container, false);
        MainApplication app = (MainApplication) getActivity().getApplication();

        user = app.user;
        name = (EditText) v.findViewById(R.id.create_team_name_input);
        description = (EditText) v.findViewById(R.id.create_team_description_input);
        service = new TeamService(new DataService.Callback() {
            @Override
            public void callback(boolean status, Object obj) {
                if(status){
                    CustomerActivity activity = (CustomerActivity) getActivity();
                    activity.addChildFragment(TeamManagementTeamList.newInstance(true));
                }
            }
        });

        Button create = (Button) v.findViewById(R.id.create_team_done);
        create.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CustomerActivity activity = (CustomerActivity) getActivity();
                String nameText = name.getText().toString();
                String descText = description.getText().toString();
                if(isEmapty(nameText) || isEmapty(descText)){
                    alertValidation("Please fill in all required text field");
                    return;
                }

                if (!isValidText(nameText)) {
                    alertValidation("Team name is incorrect format.\n" +
                            "Please use only a-z, A-Z and 0-9");
                    return;
                }

                if (!isTextShorterThan(nameText, 4)) {
                    alertValidation("Please input 4 characters or more");
                    return;
                }

                if (!isValidText(descText)) {
                    alertValidation("Team description is incorrect format.\n" +
                            "Please use only a-z, A-Z and 0-9");
                    return;
                }

                if (!isTextShorterThan(descText, 10)) {
                    alertValidation("Please input 10 characters or more");
                    return;
                }

                if(activity.isExist(nameText)){
                    alertValidation("Team name is already in use!");
                    return;
                }

                TeamManagementCreateTeam.this.service.create(TeamManagementCreateTeam.this.user.getId(), nameText, descText);
                return;
            }
        });

        Button cancel = (Button) v.findViewById(R.id.create_team_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CustomerActivity activity = (CustomerActivity) getActivity();
                activity.addChildFragment(TeamManagementTeamList.newInstance(true));
                return;
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

    public boolean isTextShorterThan(String text, int length) {
        return text.length() >= length;
    }

    public boolean isEmapty(String text) {
        return text.equals("");
    }
}
