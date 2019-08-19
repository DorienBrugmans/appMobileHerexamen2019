package com.pxl.dorienbrugmans.digibonss;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.CheckBoxPreference;
import android.support.v7.preference.Preference;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    protected boolean isLightTheme;
    protected SharedPreferences sharedPref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setPreferences();

        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        isLightTheme = sharedPref.getBoolean(getString(R.string.pref_show_bass_key), true);
        setAppTheme(isLightTheme);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button myCustomerButton = findViewById(R.id.btn_customers);
        myCustomerButton.setOnClickListener(new View.OnClickListener() {
              @Override
               public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this, CustomersListRcv.class));
                     }
                });

        Button myScheduleButton = findViewById(R.id.btn_workorders_today);
        myScheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = MainActivity.this;
                String message = "Schedule is not implemented yet!";
                Toast.makeText(context, message, Toast.LENGTH_LONG).show();            }
        });

        Button myWorkOrdersButton = findViewById(R.id.btn_workorders);
        myWorkOrdersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = MainActivity.this;
                String message = "Work Orders is not implemented yet!";
                Toast.makeText(context, message, Toast.LENGTH_LONG).show();            }
        });

        // Add toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    protected void setAppTheme(boolean isLightTheme) {
        if (isLightTheme) {
            setTheme(R.style.AppTheme);
        } else {
            setTheme(R.style.AppTheme_Dark);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        boolean currentTheme = isLightTheme;
        isLightTheme = sharedPref.getBoolean(getString(R.string.pref_show_bass_key), false);
        if (currentTheme != isLightTheme)
            recreate();
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
            Context context = MainActivity.this;
            String message = "Settings clicked";
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();

            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_schedule) {
            Context context = MainActivity.this;
            String message = "Not implemented yet!";
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
            return true;
        } else if (id == R.id.nav_workorders) {
            Context context = MainActivity.this;
            String message = "Not implemented yet!";
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
            return true;
        } else if (id == R.id.nav_customers) {
            startActivity(new Intent(MainActivity.this, CustomersListRcv.class));
            /*Context context = MainActivity.this;
            String message = "You are already in Customers view!";
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();*/
            return true;
        } else if (id == R.id.nav_settings) {
            // navigate to settings
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setPreferences() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean setTheme = preferences.getBoolean(getString(R.string.pref_show_bass_key),
                getResources().getBoolean(R.bool.pref_show_bass_default));
        if (setTheme == true){
            setTheme(R.style.AppTheme_Dark);
        }
        else{
            setTheme(R.style.AppTheme);
        }
    }



}
