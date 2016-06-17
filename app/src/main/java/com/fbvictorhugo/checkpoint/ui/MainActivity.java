package com.fbvictorhugo.checkpoint.ui;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.fbvictorhugo.checkpoint.R;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private NavigationView mNavigationView;
    private FragmentManager mFragmentManager;
    private DrawerLayout mDrawer;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFragmentManager = getSupportFragmentManager();

        findViews();
        setSupportActionBar(mToolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.setDrawerListener(toggle);
        toggle.syncState();

        mNavigationView.setNavigationItemSelectedListener(this);

        performMenuItemNavigation(R.id.nav_today);
    }

    @Override
    public void onBackPressed() {
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_today:
                navigationToTodayFragment();
                break;

            case R.id.nav_calendar:
                navigationToCalendarFragment();
                break;

            default:
                changeFragment(instanceFragment(ContentFragment.class, ContentFragment.FRAGMENT_TAG), ContentFragment.FRAGMENT_TAG);
                break;
        }

        mDrawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void findViews() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
    }

    private void performMenuItemNavigation(int menuId) {
        mNavigationView.setCheckedItem(menuId);
        mNavigationView.getMenu().performIdentifierAction(menuId, 0);
    }

    private Fragment instanceFragment(final Class fragmentClass, String fragmentTag) {
        Fragment fragment = mFragmentManager.findFragmentByTag(fragmentTag);
        if (fragment == null) {
            fragment = Fragment.instantiate(this, fragmentClass.getName());
        }
        return fragment;
    }

    private void changeFragment(final Fragment fragment, final String fragmentTag) {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.replace(R.id.content_main, fragment, fragmentTag);
        transaction.commit();
    }

    private void navigationToTodayFragment() {
        CheckpointTodayFragment fragment = (CheckpointTodayFragment) instanceFragment(CheckpointTodayFragment.class, CheckpointTodayFragment.FRAGMENT_TAG);
        changeFragment(fragment, CheckpointTodayFragment.FRAGMENT_TAG);
    }

    private void navigationToCalendarFragment() {
        CalendarFragment fragment = (CalendarFragment) instanceFragment(CalendarFragment.class, CalendarFragment.FRAGMENT_TAG);
        changeFragment(fragment, CalendarFragment.FRAGMENT_TAG);
    }

}
