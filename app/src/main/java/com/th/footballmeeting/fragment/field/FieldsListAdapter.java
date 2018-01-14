package com.th.footballmeeting.fragment.field;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.th.footballmeeting.R;
import com.th.footballmeeting.activity.CustomerActivity;
import com.th.footballmeeting.fragment.field.FieldsFragment.OnListFragmentInteractionListener;
import com.th.footballmeeting.fragment.field.dummy.DummyContent.DummyItem;
import com.th.footballmeeting.fragment.meeting.MeetingDetail;
import com.th.footballmeeting.fragment.meeting.MeetingList;
import com.th.footballmeeting.model.Field;
import com.th.footballmeeting.model.Meeting;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class FieldsListAdapter extends BaseAdapter {

    private final Activity activity;
    private final ArrayList<Field> fields;
    private static LayoutInflater inflater = null;
    private final OnListFragmentInteractionListener mListener;

    public FieldsListAdapter(Activity activity, ArrayList<Field> fields, OnListFragmentInteractionListener listener) {
        this.activity = activity;
        this.fields = fields;
        this.mListener = listener;
        inflater = (LayoutInflater) activity.getLayoutInflater();

    }

    @Override
    public int getCount() {
        return this.fields.size();
    }

    @Override
    public Object getItem(int position) {
        return this.fields.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if (convertView == null)
            vi = inflater.inflate(R.layout.fragment_fields, parent, false);
        final Field field = (Field) getItem(position);
        vi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(field);
                }
            }
        });
        TextView mIdView = (TextView) vi.findViewById(R.id.id);
        TextView mContentView = (TextView) vi.findViewById(R.id.content);
        TextView title = (TextView) vi.findViewById(R.id.title);
        TextView price = (TextView) vi.findViewById(R.id.price);
        TextView description = (TextView) vi.findViewById(R.id.description);

        LinearLayout nameSection = (LinearLayout) vi.findViewById(R.id.promotion_name_section);
        LinearLayout descSection = (LinearLayout) vi.findViewById(R.id.promotion_desc_section);


        mIdView.setText(Integer.toString(position + 1));
        mContentView.setText(field.name);
        if(field.promotion_name.equals("")){
            nameSection.setVisibility(View.GONE);
            descSection.setVisibility(View.GONE);
        } else {
            title.setText(field.promotion_name);
            price.setText(field.promotion_price);
            description.setText(field.promotion_description);
        }
        return vi;
    }
}
