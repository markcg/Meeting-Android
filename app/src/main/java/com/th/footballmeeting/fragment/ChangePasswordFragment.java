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

import com.th.footballmeeting.MainApplication;
import com.th.footballmeeting.R;
import com.th.footballmeeting.activity.CustomerActivity;
import com.th.footballmeeting.model.Customer;
import com.th.footballmeeting.services.ValidationService;
import com.th.footballmeeting.services.models.UserService;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ChangePasswordFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ChangePasswordFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChangePasswordFragment extends Fragment {
    private OnFragmentInteractionListener mListener;
    public UserService service;
    public EditText oldPassword;
    public EditText newPassword;
    public EditText confirmPassword;
    public Customer user;
    public ValidationService validation;

    public ChangePasswordFragment() {
        // Required empty public constructor
    }


    public static ChangePasswordFragment newInstance() {
        ChangePasswordFragment fragment = new ChangePasswordFragment();
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
        View v = inflater.inflate(R.layout.fragment_change_password, container, false);
        CustomerActivity activity =  (CustomerActivity) getActivity();

        MainApplication app = (MainApplication) activity.getApplication();
        this.validation = new ValidationService(activity);
        this.oldPassword = (EditText) v.findViewById(R.id.old_password);
        this.newPassword = (EditText) v.findViewById(R.id.new_password);
        this.confirmPassword = (EditText) v.findViewById(R.id.confirm_password);

        this.user = app.user;
        this.service = new UserService(new UserService.Callback() {
            @Override
            public void callback(boolean status, Object obj) {
                if(status){
                    ChangePasswordFragment.this.validation.successValidation("Password changed.");
//                    ChangePasswordFragment.this.update((Customer) obj);
                } else {
                    ChangePasswordFragment.this.validation.alertValidation("Password incorrect.");
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
                        int id = ChangePasswordFragment.this.user.getId();
                        String oldPassword = ChangePasswordFragment.this.oldPassword.getText().toString();
                        String newPassword = ChangePasswordFragment.this.newPassword.getText().toString();
                        String confirmPassword = ChangePasswordFragment.this.confirmPassword.getText().toString();
                        boolean valid = ChangePasswordFragment.this.validation.compareText(newPassword, confirmPassword);
                        if(valid){
                            ChangePasswordFragment.this.service.changePassword(id, oldPassword, newPassword);
                        } else {
                            ChangePasswordFragment.this.validation.alertValidation("Password and Confirm password is not match");
                        }
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
//        this.update(user);
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
        oldPassword.setText(user.getName());
        newPassword.setText(user.getEmail());
        confirmPassword.setText(user.getPhone_number());
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
