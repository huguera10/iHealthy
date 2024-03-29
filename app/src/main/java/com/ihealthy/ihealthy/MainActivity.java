package com.ihealthy.ihealthy;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.ParseInstallation;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // carrega a lista de restaurantes assim que carregar o app
        RestaurantFragment restaurantFragment = new RestaurantFragment();
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().
                replace(
                        R.id.relative_layout_for_fragment,
                        restaurantFragment,
                        restaurantFragment.getTag()
                ).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_restaurants) {
            RestaurantFragment restaurantFragment = new RestaurantFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().
                    replace(
                            R.id.relative_layout_for_fragment,
                            restaurantFragment,
                            restaurantFragment.getTag()
                    ).commit();

        } else if (id == R.id.nav_my_diet) {
            MyDietFragment myDietFragment = new MyDietFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().
                    replace(
                            R.id.relative_layout_for_fragment,
                            myDietFragment,
                            myDietFragment.getTag()
                    ).commit();

        } else if (id == R.id.nav_history) {
            HistoryFragment historyFragment = new HistoryFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().
                    replace(
                            R.id.relative_layout_for_fragment,
                            historyFragment,
                            historyFragment.getTag()
                    ).commit();

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_logout){
            ParseUser.logOutInBackground();
            startActivity(new Intent(this, DispatchActivity.class));
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
