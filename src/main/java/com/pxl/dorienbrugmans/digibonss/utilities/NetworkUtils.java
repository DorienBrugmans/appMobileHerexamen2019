package com.pxl.dorienbrugmans.digibonss.utilities;


import android.net.Uri;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pxl.dorienbrugmans.digibonss.R;
import com.pxl.dorienbrugmans.digibonss.dummy.DummyContent;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Scanner;


public class NetworkUtils {
    final static String GITHUB_BASE_URL =
            "https://raw.githubusercontent.com/DorienBrugmans/MobileApp2019/master/Customers.json";



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
        try {
            String customers = getResponseFromHttpUrl(buildUrl(GITHUB_BASE_URL));

            Gson gson = new Gson();

            TypeToken<List<DummyContent.Customer>> token = new TypeToken<List<DummyContent.Customer>>() {};
            List<DummyContent.Customer> customersList = gson.fromJson(customers, token.getType());

            return customersList;
        } catch(IOException e) {
            Log.e("Get Customers", e.getMessage());
            return null;
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
