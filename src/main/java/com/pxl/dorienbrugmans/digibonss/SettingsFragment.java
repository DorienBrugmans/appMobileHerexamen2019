package com.pxl.dorienbrugmans.digibonss;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.preference.PreferenceFragmentCompat;

import static android.support.v4.content.ContextCompat.getSystemService;


//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.app.NotificationManager;
//import android.support.v4.app.NotificationCompat;
//import android.view.View;
//import android.content.Context;
//import android.app.PendingIntent;
//import android.content.Intent;
//import android.net.Uri;

public class SettingsFragment extends PreferenceFragmentCompat implements
        SharedPreferences.OnSharedPreferenceChangeListener {
    private final String CHANNEL_ID = "personal_notifications";
    private final int NOTIFICATION_ID = 001;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        // Add visualizer preferences, defined in the XML file in res->xml->pref_visualizer
        addPreferencesFromResource(R.xml.pref_visualizer);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        addSettingsNotification();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPreferenceScreen().getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);
        createNotificationChannel();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getPreferenceScreen().getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(this);
    }

    private void addSettingsNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this.getContext().getApplicationContext(), CHANNEL_ID)
                .setSmallIcon(R.drawable.logo)
                .setContentTitle("Preferences where changed")
                .setContentText("You have changed your color scheme preference in your settings")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);


        Intent notificationIntent = new Intent(this.getContext().getApplicationContext(), SettingsActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this.getContext().getApplicationContext(), 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        NotificationManager notificationManager = (NotificationManager) this.getContext().getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);;

        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }


    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(this.getContext(), NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
