package com.pxl.dorienbrugmans.digibonss;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class SettingsActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {
    protected boolean isLightTheme;
    protected SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        isLightTheme = sharedPref.getBoolean(getString(R.string.pref_show_bass_key), true);
        setAppTheme(isLightTheme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setTitle(R.string.action_settings);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        sharedPref.registerOnSharedPreferenceChangeListener(this);
    }

    protected void setAppTheme(boolean isLightTheme) {
        if (isLightTheme) {
            setTheme(R.style.AppThemeSettings);
        } else {
            setTheme(R.style.AppThemeSettingsDark);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sharedPref.unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(getString(R.string.pref_show_bass_key))) {
            Log.d("Settings - preferences", "Color theme is changed!");
            recreate();
        }
    }
}
