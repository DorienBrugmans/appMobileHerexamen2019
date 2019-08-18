package com.pxl.dorienbrugmans.digibonss;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customers_list_rcv);

        makeGithubSearchQuery();
/*
        Customers.add(new DummyContent.Customer(1, "Dorien", "Engelbamp", "0123456789", "logo"));
        Customers.add(new DummyContent.Customer(2, "Joeri", "Engelbamp", "0123456789", "badkamer"));
        Customers.add(new DummyContent.Customer(3, "Yuna", "Engelbamp", "0123456789", "badkamer"));
        Customers.add(new DummyContent.Customer(4, "Ymke", "Engelbamp", "0123456789", "logo"));
        Customers.add(new DummyContent.Customer(5, "Yinthe", "Engelbamp", "0123456789", "logo"));
        Customers.add(new DummyContent.Customer(6, "Laika", "Engelbamp", "0123456789", "badkamer"));
        Customers.add(new DummyContent.Customer(7, "Lexa", "Engelbamp", "0123456789", "logo"));

        StringBuffer response = new StringBuffer();
        HttpURLConnection conn = null;
        try {
            URL url = new URL("https://raw.githubusercontent.com/DorienBrugmans/appMobileHerexamen2019/master/Customers.json");

            conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(false);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");

            // handle the response
            int status = conn.getResponseCode();
            if (status != 200) {
                throw new IOException("Post failed with error code " + status);
            } else {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
            }
        } catch (Exception e) {
            //e.printStackTrace();
            Log.e("Error HTTP Call: ", e.getMessage());
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }

        //Here is your json in string format
        String responseJSON = response.toString();
        Log.i("json string:::: ", responseJSON);


*/


        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        ArrayList<DummyContent.Customer> newList = new ArrayList<>();
        newList = gson.fromJson(responseJSON, new TypeToken<List<DummyContent.Customer>>(){}.getType());

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CustomersListAdapter(this, newList); // newList
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
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
        Intent intent = new Intent(CustomersListRcv.this, CustomerDetails.class);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
