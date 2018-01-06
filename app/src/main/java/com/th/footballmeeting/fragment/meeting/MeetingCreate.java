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
import android.widget.Button;
import android.widget.EditText;

import com.th.footballmeeting.MainApplication;
import com.th.footballmeeting.activity.CustomerActivity;
import com.th.footballmeeting.activity.MainActivity;
import com.th.footballmeeting.R;
import com.th.footballmeeting.model.Customer;
import com.th.footballmeeting.model.Meeting;
import com.th.footballmeeting.services.DataService;
import com.th.footballmeeting.services.models.MeetingService;

import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MeetingCreate.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MeetingCreate#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MeetingCreate extends Fragment {
    public MeetingService service;
    public Customer user;
    private OnFragmentInteractionListener mListener;

    public MeetingCreate() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MeetingCreate.
     */
    // TODO: Rename and change types and number of parameters
    public static MeetingCreate newInstance() {
        MeetingCreate fragment = new MeetingCreate();
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
        View v = inflater.inflate(R.layout.fragment_meeting_create, container, false);
        MainApplication app = (MainApplication) getActivity().getApplication();

        this.user = app.user;
        this.service = new MeetingService(new DataService.Callback() {
            @Override
            public void callback(boolean status, Object obj) {
                CustomerActivity activity = (CustomerActivity) getActivity();
                activity.addChildFragment(MeetingList.newInstance());
            }
        });

        final CustomerActivity activity =  (CustomerActivity) getActivity();
        final EditText name = (EditText) v.findViewById(R.id.meeting_name_input);
        final EditText desc = (EditText) v.findViewById(R.id.meeting_desc_input);
        final EditText date = (EditText) v.findViewById(R.id.meeting_date);
        final EditText start = (EditText) v.findViewById(R.id.meeting_time_start);
        final EditText end = (EditText) v.findViewById(R.id.meeting_time_end);

        Button confirm = (Button) v.findViewById(R.id.create_meeting_done);
        confirm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String nameText = name.getText().toString();
                String descText = desc.getText().toString();
                String dateText = date.getText().toString();
                String startText = start.getText().toString();
                String endText = end.getText().toString();
                if(isEmapty(nameText) || isEmapty(descText) || isEmapty(startText) || isEmapty(endText)){
                    alertValidation("Please fill in all required text field");
                    return;
                }

                if (!isValidText(nameText)) {
                    alertValidation("“Meeting name is incorrect format.\n" +
                            "Please use only a-z, A-Z and 0-9");
                    return;
                }

                if (!isTextShorterThan(nameText, 4)) {
                    alertValidation("Please input 4 characters or more");
                    return;
                }

                if (!isValidText(descText)) {
                    alertValidation("“Meeting description is incorrect format.\n" +
                            "Please use only a-z, A-Z and 0-9");
                    return;
                }

                if (!isTextShorterThan(descText, 10)) {
                    alertValidation("Please input 10 characters or more");
                    return;
                }

                if (!isValidTime(startText)) {
                    alertValidation("Start is incorrect format.\n" +
                            "Please use inly 0-9 in HH:MM format");
                    return;
                }

                if (!isValidTime(endText)) {
                    alertValidation("End is incorrect format.\n" +
                            "Please use inly 0-9 in HH:MM format");
                    return;
                }

                MeetingCreate.this.service.create(MeetingCreate.this.user.getId(), nameText, dateText, startText, endText, descText);
                return;
            }
        });

        Button cancel = (Button) v.findViewById(R.id.create_meeting_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                activity.addChildFragment(MeetingList.newInstance());
            }
        });
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

    public boolean isValidTime(String name) {
        Pattern p = Pattern.compile("[0-9][0-9]\\:[0-9]?[0-9]");
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
