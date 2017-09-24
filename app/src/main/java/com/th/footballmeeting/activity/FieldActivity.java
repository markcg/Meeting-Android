package com.th.footballmeeting.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.th.footballmeeting.R;
import com.th.footballmeeting.fragment.Home;
import com.th.footballmeeting.fragment.MeetingFragment;
import com.th.footballmeeting.fragment.RequestFragment;
import com.th.footballmeeting.fragment.TeamManagement;
import com.th.footballmeeting.fragment.meeting.MeetingCreate;
import com.th.footballmeeting.fragment.meeting.MeetingDetail;
import com.th.footballmeeting.fragment.meeting.MeetingList;
import com.th.footballmeeting.fragment.meeting.MeetingTeamInvite;
import com.th.footballmeeting.fragment.request.ReservationFragment;
import com.th.footballmeeting.fragment.team_management.TeamManagementCreateTeam;
import com.th.footballmeeting.fragment.team_management.TeamManagementTeamDetail;
import com.th.footballmeeting.fragment.team_management.TeamManagementTeamInvite;
import com.th.footballmeeting.fragment.team_management.TeamManagementTeamList;
import com.th.footballmeeting.model.Request;
import com.th.footballmeeting.model.Team;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedList;

public class FieldActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
        /* Home Fragment */
        , Home.OnFragmentInteractionListener
        /* RequestFragment */
        , RequestFragment.OnFragmentInteractionListener
        /* MeetingFragment Fragement */
        , ReservationFragment.OnFragmentInteractionListener {
    private LinkedList<Fragment> history;
    private boolean isInFragment = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.history = new LinkedList<Fragment>();

        setContentView(R.layout.activity_field);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

//        Class defaultFragment = Home.class;
        Fragment fragment = (Fragment) new Home();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.main_fragment_container, fragment).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            if (this.history.peek() != null) {
                Fragment parent = this.history.pop();
                fragmentManager.beginTransaction().replace(R.id.main_fragment_container, parent).commit();
                return;
            }
            super.onBackPressed();
//            this.isInFragment = false;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            Intent intent = new Intent(FieldActivity.this, CustomerLoginActivity.class);
            FieldActivity.this.startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = null;
        Class fragmentClass = null;

        if (id == R.id.nav_request) {
            fragmentClass = RequestFragment.class;
        } else if (id == R.id.nav_profile) {
            return true;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.history.push(fragment);
        this.isInFragment = true;
        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.main_fragment_container, fragment).commit();

        // Highlight the selected item has been done by NavigationView
        item.setChecked(true);
        // Set action bar title
        setTitle(item.getTitle());

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void addChildFragment(Fragment child, Fragment parent) {
        this.history.push(parent);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.main_fragment_container, child).commit();
    }

    public void addChildFragment(Fragment child) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.main_fragment_container, child).commit();
    }

    /* Mock Up */
    public ArrayList<Request> getRequestList() {
        ArrayList<Request> requests = new ArrayList<Request>();
        requests.add(new Request("Messi", "22/09/2017"));
        requests.add(new Request("Messi", "22/09/2017"));
        requests.add(new Request("Ronaldo", "22/09/2017"));
        requests.add(new Request("Toure", "22/09/2017"));
        requests.add(new Request("Toure", "22/09/2017"));
        return requests;
    }
}
