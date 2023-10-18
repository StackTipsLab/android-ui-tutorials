package com.javatechig.navigationdrawer;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

    /* Within which the entire activity is enclosed */
    private DrawerLayout mDrawerLayout;

    /* ListView represents Navigation Drawer */
    private ListView mDrawerList;

    /* ActionBarDrawerToggle indicates the presence of Navigation Drawer in the action bar */
    private ActionBarDrawerToggle mDrawerToggle;

    /* Title of the action bar */
    private String mTitle = "Navigation Drawer";

    /* Getting navigation items from array */
    private String[] items;

    private int selectedPosition;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Setting actionbar title */
        getActionBar().setTitle(mTitle);

        items = getResources().getStringArray(R.array.menus);

		/* Getting reference to the DrawerLayout */
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.drawer_list);

        /* Creating an ArrayAdapter to add items to mDrawerList */
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, items);

		/* Setting the adapter to mDrawerList */
        mDrawerList.setAdapter(adapter);

		/* Getting reference to the ActionBarDrawerToggle */
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close) {

            /* Called when drawer is closed */
            public void onDrawerClosed(View view) {
                //Put your code here
            }

            /* Called when a drawer is opened */
            public void onDrawerOpened(View drawerView) {
                //Put your code here
            }
        };

		/* Setting DrawerToggle on DrawerLayout */
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        // Enabling Home button
        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        // Setting item click listener for the listview mDrawerList
        mDrawerList.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedPosition = position;

                /* Replace fragment content */
                updateFragment();

				/* Closing the drawer */
                mDrawerLayout.closeDrawer(mDrawerList);
            }
        });

        /* Setting default fragment */
        selectedPosition = 0;
        updateFragment();
    }

    public void updateFragment() {
        /* Getting reference to the FragmentManager */
        FragmentManager fragmentManager = getFragmentManager();

        /* Creating fragment instance */
        WebViewFragment rFragment = new WebViewFragment();

        /* Passing selected item information to fragment */
        Bundle data = new Bundle();
        data.putString("title", items[selectedPosition]);
        data.putString("url", getUrl());
        rFragment.setArguments(data);

        /* Replace fragment */
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.content_frame, rFragment);
        ft.commit();
    }

    protected String getUrl() {
        StringBuffer url = new StringBuffer("http://javatechig.com");
        if (selectedPosition > 0) {
            url.append("/topics/" + items[selectedPosition].toLowerCase());
        }
        return url.toString();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }
}
