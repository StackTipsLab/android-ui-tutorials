package com.javatechig.apps.nav;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * @author Nilanchala panigrahy
 */

public class HomeActivity extends ActionBarActivity implements NavigationCallbacks {

    private Toolbar mToolbar;

    private NavigationDrawerFragment mNavigationDrawerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Setting toolbar as actionbar */
        mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        /* Initializing NavigationDrawer fragment */
        mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager().findFragmentById(R.id.fragment_drawer);
        mNavigationDrawerFragment.initDrawer(R.id.fragment_drawer, (DrawerLayout) findViewById(R.id.drawer), mToolbar);

        onItemSelected(0);
    }

    @Override
    public void onItemSelected(int selectedIdx) {
        //Toast.makeText(this, "Menu item selected -> " + selectedIdx, Toast.LENGTH_SHORT).show();

        StringBuffer url = new StringBuffer ("http://javatechig.com");
        switch (selectedIdx) {
            case 0:
                url.append("/topics/android");
                break;
            case 1:
                url.append("/topics/java");
                break;
            case 2:
                url.append("/topics/design-patterns");
                break;
            case 3:
                url.append("/topics/xamarin");
                break;
            case 4:
                url.append("/topics/data-structure");
            default:
                break;
        }

        Bundle bundle = new Bundle();
        bundle.putString("url-key", url.toString());
        Fragment fragment = WebViewFragment.getInstance(bundle);

        addFragment(fragment);
    }

    private void addFragment(Fragment fragment) {
        if (fragment != null) {
            /* Getting FragmentManager reference */
            FragmentManager fragmentManager = getSupportFragmentManager();

            /* Create fragment transaction */
            FragmentTransaction ft = fragmentManager.beginTransaction();

            /* Adding a fragment to the fragment transaction */
            ft.replace(R.id.container, fragment);

            /* Committing the transaction */
            ft.commit();
        }
    }

    @Override
    public void onBackPressed() {
        /* Close navigation drawer, if open */
        if (mNavigationDrawerFragment.isDrawerOpen())
            mNavigationDrawerFragment.closeDrawer();
        else
            super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_about:
                Toast.makeText(this, "About Clicked!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_help:
                Toast.makeText(this, "Help Clicked!", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
