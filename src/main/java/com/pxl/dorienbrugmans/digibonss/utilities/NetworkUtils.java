package com.pxl.dorienbrugmans.digibonss.utilities;


import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pxl.dorienbrugmans.digibonss.CustomersListRcv;
import com.pxl.dorienbrugmans.digibonss.R;
import com.pxl.dorienbrugmans.digibonss.dummy.DummyContent;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class NetworkUtils {
    final static String GITHUB_BASE_URL =
            "https://raw.githubusercontent.com/DorienBrugmans/MobileApp2019/master/Customers.json";

    static String responseJSON = "";


    //*************************************************************************************
    public static URL buildUrl(String urlString) {
        Uri builtUri = Uri.parse(urlString).buildUpon()
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    public static List<DummyContent.Customer> GetCustomers() {
        makeGithubSearchQuery();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        ArrayList<DummyContent.Customer> newList = new ArrayList<>();
        newList = gson.fromJson(responseJSON, new TypeToken<List<DummyContent.Customer>>(){}.getType());

        return newList;
    }

    private static void makeGithubSearchQuery() {
        String githubQuery = GITHUB_BASE_URL;
        URL githubSearchUrl = NetworkUtils.buildUrl(githubQuery);
        new GithubQueryTask().execute(githubSearchUrl);
    }

    public static class GithubQueryTask extends AsyncTask<URL, Void, String> {
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




    public static String getResponseFromHttpUrl(URL url) throws IOException {
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                InputStream in = urlConnection.getInputStream();

                Scanner scanner = new Scanner(in);
                scanner.useDelimiter("\\A");

                boolean hasInput = scanner.hasNext();
                if (hasInput) {
                    return scanner.next();
                } else {
                    return null;
                }
            } finally {
                urlConnection.disconnect();
            }
        }
}
