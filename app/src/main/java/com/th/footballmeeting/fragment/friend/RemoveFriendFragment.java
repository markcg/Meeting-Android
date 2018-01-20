package com.th.footballmeeting.fragment.friend;

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

import com.th.footballmeeting.MainApplication;
import com.th.footballmeeting.R;
import com.th.footballmeeting.activity.CustomerActivity;
import com.th.footballmeeting.adapter.FriendAddListAdapter;
import com.th.footballmeeting.adapter.FriendRemoveListAdapter;
import com.th.footballmeeting.model.Customer;
import com.th.footballmeeting.services.models.UserService;

import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RemoveFriendFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RemoveFriendFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RemoveFriendFragment extends Fragment {
    public ArrayList<Customer> members;
    static UserService service;
    public int mode;
    private OnFragmentInteractionListener mListener;

    public RemoveFriendFragment() {}

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment RemoveFriendFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RemoveFriendFragment newInstance(int mode) {
        RemoveFriendFragment fragment = new RemoveFriendFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        fragment.mode = mode;
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
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_remove_friend, container, false);
        final CustomerActivity activity = (CustomerActivity) getActivity();
        members = activity.getCustomerList();

        final ListView list = (ListView) v.findViewById(R.id.search_list);
        service = new UserService(new UserService.CallbackList() {
            @Override
            public void callback(boolean status, ArrayList<?> obj) {
                if(status){
                    FriendRemoveListAdapter adapter = new FriendRemoveListAdapter(getActivity(), RemoveFriendFragment.this, (ArrayList<Customer>)obj, RemoveFriendFragment.this.mode);
                    list.setAdapter(adapter);
                }
            }
        });
        if(this.mode == 1) {
            service.friends(((MainApplication) getActivity().getApplication()).user.getId());
        } else {
            service.friendsRequest(((MainApplication) getActivity().getApplication()).user.getId());
        }
        final EditText search = (EditText) v.findViewById(R.id.search_input);
        search.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus) {
                    ArrayList<Customer> result = activity.searchCustomer(search.getText().toString());

                    if (!isValidText(search.getText().toString())) {
                        alertValidation("Username or name is incorrect format.\n" +
                                "Please use only a-z, A-Z and 0-9");
                        return;
                    }

                    if(result.size() <= 0){
                        alertValidation("“Username or name is incorrect”");
                        return;
                    }
                    ((FriendAddListAdapter) list.getAdapter()).friends = result;
                    ((FriendAddListAdapter) list.getAdapter()).notifyDataSetChanged();
                }

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
    /* Reload */
    public void reloadFriend(){
        if(this.mode == 1) {
            service.friends(((MainApplication) getActivity().getApplication()).user.getId());
        } else {
            service.friendsRequest(((MainApplication) getActivity().getApplication()).user.getId());
        }
//        service.friends(((MainApplication) getActivity().getApplication()).user.getId());
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
}
