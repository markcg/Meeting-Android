package com.th.footballmeeting.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.th.footballmeeting.R;
import com.th.footballmeeting.activity.CustomerActivity;
import com.th.footballmeeting.fragment.reserve.ScheduleFragment;
import com.th.footballmeeting.model.Schedule;
import com.th.footballmeeting.model.ScheduleSearch;
import com.th.footballmeeting.services.DataService;
import com.th.footballmeeting.services.ValidationService;
import com.th.footballmeeting.services.models.FieldService;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ReserveFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ReserveFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReserveFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    // TODO: Rename and change types of parameters
    ValidationService validationService;
    FieldService service;
    private OnFragmentInteractionListener mListener;

    public ReserveFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ReserveFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ReserveFragment newInstance() {
        ReserveFragment fragment = new ReserveFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_reserve, container, false);
        // Inflate the layout for this fragment
        validationService = new ValidationService(this.getActivity());
        final EditText name = (EditText) v.findViewById(R.id.name);
        final EditText date = (EditText) v.findViewById(R.id.date);

        this.service = new FieldService(new DataService.Callback() {
            @Override
            public void callback(boolean status, Object obj) {
                if (status) {
                    String dateText = date.getText().toString();
                    ScheduleSearch reserves = (ScheduleSearch) obj;
                    ((CustomerActivity) getActivity()).addChildFragment(ScheduleFragment.newInstance(reserves, dateText), ReserveFragment.newInstance());
                }

            }
        });

        Button search = (Button) v.findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String nameText = name.getText().toString();
                String dateText = date.getText().toString();
                if (validationService.isEmapty(nameText) || validationService.isEmapty(dateText)) {
                    validationService.alertValidation("Please fill in all required text field");
                    return;
                }

                if (!validationService.isValidText(nameText)) {
                    validationService.alertValidation("“Meeting name is incorrect format.\n" +
                            "Please use only a-z, A-Z and 0-9");
                    return;
                }

                if (!validationService.isTextShorterThan(nameText, 4)) {
                    validationService.alertValidation("Please input 4 characters or more");
                    return;
                }
                service.searchByName(nameText, dateText);
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
