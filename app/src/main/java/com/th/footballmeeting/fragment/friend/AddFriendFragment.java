package com.th.footballmeeting.fragment.friend;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import com.th.footballmeeting.MainApplication;
import com.th.footballmeeting.R;
import com.th.footballmeeting.activity.CustomerActivity;
import com.th.footballmeeting.adapter.FriendAddListAdapter;
import com.th.footballmeeting.model.Customer;
import com.th.footballmeeting.services.ValidationService;
import com.th.footballmeeting.services.models.UserService;

import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddFriendFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddFriendFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddFriendFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    // TODO: Rename and change types of parameters
    public ArrayList<Customer> members;
    public CustomerActivity activity;
    public UserService service;
    public ListView list;
    public int customerId;

    private OnFragmentInteractionListener mListener;

    public AddFriendFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment AddFriendFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddFriendFragment newInstance() {
        AddFriendFragment fragment = new AddFriendFragment();
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
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add_friend, container, false);
        MainApplication application = (MainApplication) getActivity().getApplication();
        this.customerId = application.user.getId();

        this.activity = (CustomerActivity) getActivity();
        this.list = (ListView) v.findViewById(R.id.search_list);
        this.service = new UserService(new UserService.CallbackList() {
            @Override
            public void callback(boolean status, ArrayList<?> obj) {
                if(status){
                    AddFriendFragment.this.members = (ArrayList<Customer>)obj;
                    FriendAddListAdapter adapter = new FriendAddListAdapter(getActivity(), AddFriendFragment.this, (ArrayList<Customer>)obj, customerId);
                    AddFriendFragment.this.list.setAdapter(adapter);
                }
            }
        });

        service.searchNewFriend("", customerId);

        final EditText search = (EditText) v.findViewById(R.id.search_input);
        search.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus) {
                    String keyword = search.getText().toString();
                    int id = AddFriendFragment.this.customerId;

                    if (!isValidText(search.getText().toString())) {
                        alertValidation("Username or name is incorrect format.\n" +
                                "Please use only a-z, A-Z and 0-9");
                        return;
                    } else {
                        AddFriendFragment.this.service.searchNewFriend(keyword, id);
                    }

//                    if(result.size() <= 0){
//                        alertValidation("“Username or name is incorrect”");
//                        return;
//                    }
//                    ((FriendAddListAdapter) list.getAdapter()).friends = result;
//                    ((FriendAddListAdapter) list.getAdapter()).notifyDataSetChanged();
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
        service.searchNewFriend("", AddFriendFragment.this.customerId);
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
