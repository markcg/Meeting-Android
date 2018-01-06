package com.th.footballmeeting.fragment.field;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.th.footballmeeting.R;
import com.th.footballmeeting.activity.CustomerActivity;
import com.th.footballmeeting.activity.FieldMapsActivity;
import com.th.footballmeeting.fragment.ReserveFragment;
import com.th.footballmeeting.fragment.reserve.ScheduleFragment;
import com.th.footballmeeting.model.Field;
import com.th.footballmeeting.model.Meeting;
import com.th.footballmeeting.model.ScheduleSearch;
import com.th.footballmeeting.services.DataService;
import com.th.footballmeeting.services.models.FieldService;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FieldFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FieldFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FieldFragment extends Fragment {

    public Field field;
    public Meeting meeting;
    public FieldService service;
    private OnFragmentInteractionListener mListener;

    public FieldFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment FieldFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FieldFragment newInstance(Field field, Meeting meeting) {
        FieldFragment fragment = new FieldFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
        fragment.field = field;
        fragment.meeting = meeting;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_field, container, false);

        this.service = new FieldService(new DataService.Callback() {
            @Override
            public void callback(boolean status, Object obj) {
                if (status) {
                    String dateText = FieldFragment.this.meeting.date;
                    ScheduleSearch reserves = (ScheduleSearch) obj;
                    ((CustomerActivity) getActivity()).addChildFragment(ScheduleFragment.newInstance(reserves, dateText), FieldFragment.newInstance(field,meeting));
                }

            }
        });
        TextView name = (TextView) v.findViewById(R.id.name);
        name.setText(field.name);
        TextView description = (TextView) v.findViewById(R.id.description);
        description.setText(field.description);
        TextView address = (TextView) v.findViewById(R.id.address);
        address.setText(field.address);
        TextView contact = (TextView) v.findViewById(R.id.contact);
        contact.setText(field.phone_number);

        Button map = (Button) v.findViewById(R.id.map);
        map.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FieldMapsActivity.class);
                intent.putExtra("lat", Double.parseDouble(field.latitude));
                intent.putExtra("lng", Double.parseDouble(field.longitude));
                startActivity(intent);
            }
        });

        Button search = (Button) v.findViewById(R.id.reserve);
        search.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int id = FieldFragment.this.field.id;
                String date = FieldFragment.this.meeting.date;
                service.searchById(id, date);
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
