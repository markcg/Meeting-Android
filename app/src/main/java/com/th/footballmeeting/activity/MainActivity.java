package com.th.footballmeeting.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.th.footballmeeting.R;
import com.th.footballmeeting.fragment.Home;
import com.th.footballmeeting.fragment.MeetingFragment;
import com.th.footballmeeting.fragment.TeamManagement;
import com.th.footballmeeting.fragment.meeting.MeetingCreate;
import com.th.footballmeeting.fragment.meeting.MeetingDetail;
import com.th.footballmeeting.fragment.meeting.MeetingList;
import com.th.footballmeeting.fragment.meeting.MeetingTeamInvite;
import com.th.footballmeeting.fragment.team_management.TeamManagementCreateTeam;
import com.th.footballmeeting.fragment.team_management.TeamManagementTeamDetail;
import com.th.footballmeeting.fragment.team_management.TeamManagementTeamInvite;
import com.th.footballmeeting.fragment.team_management.TeamManagementTeamList;
import com.th.footballmeeting.model.Meeting;
import com.th.footballmeeting.model.Member;
import com.th.footballmeeting.model.Team;

import java.util.ArrayList;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener
        , Home.OnFragmentInteractionListener
        /* MeetingFragment Fragement */
        , MeetingFragment.OnFragmentInteractionListener
        , MeetingCreate.OnFragmentInteractionListener
        , MeetingDetail.OnFragmentInteractionListener
        , MeetingList.OnFragmentInteractionListener
        , MeetingTeamInvite.OnFragmentInteractionListener
        /* Team Management Fragment */
        , TeamManagement.OnFragmentInteractionListener
        , TeamManagementCreateTeam.OnFragmentInteractionListener
        , TeamManagementTeamDetail.OnFragmentInteractionListener
        , TeamManagementTeamInvite.OnFragmentInteractionListener
        , TeamManagementTeamList.OnFragmentInteractionListener {
    private LinkedList<Fragment> history;
    private boolean isInFragment = false;
    private ArrayList<Team> teams = new ArrayList<Team>();
    private ArrayList<Member> members = new ArrayList<Member>();
    private ArrayList<Meeting> meetings = new ArrayList<Meeting>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.teams = this.getTeamList();
        this.members = this.getMemberList();
        this.history = new LinkedList<Fragment>();

        setContentView(R.layout.activity_main);
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

        if (id == R.id.nav_meeting) {
            fragmentClass = MeetingFragment.class;
        } else if (id == R.id.nav_team) {
            fragmentClass = TeamManagement.class;
        } else if (id == R.id.nav_profile) {

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

    /* Member */
    public ArrayList<Member> getMemberList() {
        ArrayList<Member> members = new ArrayList<Member>();
        members.add(new Member("Lionel Messi"));
        members.add(new Member("Cristiano Ronaldo"));
        members.add(new Member("Xavi"));
        members.add(new Member("Andres Iniesta"));
        members.add(new Member("Zlatan Ibrahimovic"));
        members.add(new Member("Radamel Falcao"));
        members.add(new Member("Robin van Persie"));
        members.add(new Member("Andrea Pirlo"));
        members.add(new Member("Yaya Toure"));
        members.add(new Member("Edinson Cavani"));
        return members;
    }

    public void addMember(Member member) {
        this.members.add(member);
    }

    public ArrayList<Member> searchMember(String keyword) {
        ArrayList<Member> members = new ArrayList<Member>();
        for (Member member : getMemberList()) {
            if(member.getName().contains(keyword)) {
                members.add(member);
            }
        }
        return members;
    }

    /* Team */

    public ArrayList<Team> getTeams() {
        return teams;
    }

    public Team getTeam(int id) {
        return this.teams.get(id);
    }

    public void setTeams(ArrayList<Team> teams) {
        this.teams = teams;
    }

    public void addTeam(Team team) {
        this.teams.add(team);
    }

    public void addTeamMember(int teamId, Member member) {
        Team team = this.teams.get(teamId);
        team.addMember(member);
        this.teams.set(teamId, team);
    }

    public void removeTeamMember(int teamId, int memberId) {
        Team team = this.teams.get(teamId);
        team.removeMember(memberId);
        this.teams.set(teamId, team);
    }

    public ArrayList<Team> getTeamList() {
        ArrayList<Team> teams = new ArrayList<Team>();
        teams.add(new Team("Team 1", "First Team", this.getMemberList()));
        teams.add(new Team("Team 2", "Second Team", this.getMemberList()));
        teams.add(new Team("Team 3", "Third Team", this.getMemberList()));
        teams.add(new Team("Team 4", "Fourth Team", this.getMemberList()));
        teams.add(new Team("Team 5", "Fifth Team", this.getMemberList()));
        return teams;
    }

    public ArrayList<Team> searchTeam(String keyword) {
        ArrayList<Team> teams = new ArrayList<Team>();
        for (Team team : getTeamList()) {
            if(team.getName().contains(keyword)) {
                teams.add(team);
            }
        }
        return teams;
    }
    /* Meeting */
    public Meeting getMeeting(int id) {
        return this.meetings.get(id);
    }

    public void addMeeting(Meeting meeting) {
        this.meetings.add(meeting);
    }

    public ArrayList<Meeting> getMeeting() {
        return meetings;
    }

    public void addMeetingTeam(int meetingId, Team team) {
        Meeting meeting = this.meetings.get(meetingId);
        meeting.addTeam(team);
        this.meetings.set(meetingId, meeting);
    }

    public void removeMeetingTeam(int meetingId, int teamId) {
        Meeting meeting = this.meetings.get(meetingId);
        meeting.removeTeam(teamId);
//        this.meetings.set(meetingId, meeting);
    }

    public boolean isExist(String name) {
        for (Team team : this.teams) {
            return team.getName().toString().equals(name);
        }
        return false;
    }

}
