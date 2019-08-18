package com.pxl.dorienbrugmans.digibonss;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v4.app.NavUtils;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pxl.dorienbrugmans.digibonss.dummy.DummyContent;
import com.pxl.dorienbrugmans.digibonss.utilities.NetworkUtils;
import com.pxl.dorienbrugmans.digibonss.utilities.Utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class CustomersListRcv extends AppCompatActivity implements CustomersListAdapter.ItemClickListener {

    CustomersListAdapter adapter;
    List<DummyContent.Customer> Customers = new ArrayList<>();
    String url = "https://raw.githubusercontent.com/DorienBrugmans/appMobileHerexamen2019/master/Customers.json";
    String responseJSON = "";

    private boolean mTwoPane;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setPreferences();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        if (findViewById(R.id.customer_detail_container) != null) {
            // The detail container view will be present only in the
            // landscape mode.
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        makeGithubSearchQuery();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        //ArrayList<DummyContent.Customer> newList = new ArrayList<>();
        Customers = gson.fromJson(responseJSON, new TypeToken<List<DummyContent.Customer>>(){}.getType());

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.customer_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CustomersListAdapter(this, Customers, mTwoPane); // newList
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
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

    private void makeGithubSearchQuery() {
        String githubQuery = url;
        URL githubSearchUrl = NetworkUtils.buildUrl(githubQuery);
        new GithubQueryTask().execute(githubSearchUrl);
    }

    public class GithubQueryTask extends AsyncTask<URL, Void, String> {
        @Override
        protected String doInBackground(URL... params) {
            URL searchUrl = params[0];
            String githubSearchResults = null;
            try {
                githubSearchResults = NetworkUtils.getResponseFromHttpUrl(searchUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
            responseJSON = githubSearchResults;
            return githubSearchResults;
        }

        @Override
        protected void onPostExecute(String githubSearchResults) {
            if (githubSearchResults != null && !githubSearchResults.equals("")) {
                //mSearchResultsTextView.setText(githubSearchResults);
                responseJSON = githubSearchResults;
            }
        }
    }



    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(CustomersListRcv.this, CustomerDetailActivity.class);
        int id = Customers.get(position).getId();
        intent.putExtra("Id", id);
        startActivity(intent);
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
            Context context = CustomersListRcv.this;
            String message = "Settings clicked";
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
            Intent intent = new Intent(CustomersListRcv.this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. Use NavUtils to allow users
            // to navigate up one level in the application structure. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setPreferences() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean setTheme = preferences.getBoolean(getString(R.string.pref_show_bass_key),
                getResources().getBoolean(R.bool.pref_show_bass_default));
        if (setTheme == true){
            setTheme(R.style.AppTheme);
        }
        else{
            setTheme(R.style.AppTheme_Dark);
        }
    }
}
