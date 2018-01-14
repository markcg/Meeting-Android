package com.th.footballmeeting.fragment;

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
import android.widget.ListView;

import com.th.footballmeeting.MainApplication;
import com.th.footballmeeting.R;
import com.th.footballmeeting.activity.CustomerActivity;
import com.th.footballmeeting.adapter.MeetingListAdapter;
import com.th.footballmeeting.adapter.MeetingTeamListAdapter;
import com.th.footballmeeting.fragment.meeting.MeetingList;
import com.th.footballmeeting.model.Customer;
import com.th.footballmeeting.model.Meeting;
import com.th.footballmeeting.services.models.UserService;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProfileFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public UserService service;
    public EditText name;
    public EditText email;
    public EditText phone;
    public Customer user;
    private OnFragmentInteractionListener mListener;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
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
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        CustomerActivity activity =  (CustomerActivity) getActivity();

        MainApplication app = (MainApplication) activity.getApplication();
        this.name = (EditText) v.findViewById(R.id.name);
        this.email = (EditText) v.findViewById(R.id.email);
        this.phone = (EditText) v.findViewById(R.id.phone);

        this.user = app.user;
        this.service = new UserService(new UserService.Callback() {
            @Override
            public void callback(boolean status, Object obj) {
                if(status){
                    CustomerActivity activity =  (CustomerActivity) getActivity();
                    MainApplication app = (MainApplication) activity.getApplication();
                    app.user = (Customer) obj;
                    ProfileFragment.this.update((Customer) obj);
                }
            }
        });
        Button button = (Button) v.findViewById(R.id.confirm);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(v.getContext());
                alert.setTitle("Save");
                alert.setMessage("Are you sure you want to save?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int id = ProfileFragment.this.user.getId();
                        String name = ProfileFragment.this.name.getText().toString();
                        String email = ProfileFragment.this.email.getText().toString();
                        String phone = ProfileFragment.this.phone.getText().toString();

                        ProfileFragment.this.service.edit(id, name, email, phone);
                        dialog.dismiss();
                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alert.show();
            }
        });
        this.update(user);
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

    public void update(Customer user){
        name.setText(user.getName());
        email.setText(user.getEmail());
        phone.setText(user.getPhone_number());
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
