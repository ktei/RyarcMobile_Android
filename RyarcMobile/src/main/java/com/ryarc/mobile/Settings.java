package com.ryarc.mobile;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class Settings {
    public static final String DEFAULT_APP_URL = "http://192.168.0.13/RyarcMobile";
    private static final String PREFS_SETTINGS = "PREFS_SETTINGS";
    private static final String KEY_APP_URL = "KEY_APP_URL";
    private SharedPreferences sharedPrefs;
    private String appURL;
    private static Settings _instance;

    public static Settings getInstance(Activity activity) {
        if (_instance == null) {
            _instance = new Settings(activity);
        }
        return _instance;
    }

    private Settings(Activity activity) {
        this.sharedPrefs = activity.getSharedPreferences(PREFS_SETTINGS, Context.MODE_PRIVATE);
        this.load();
    }

    private void load() {
        appURL = sharedPrefs.getString(KEY_APP_URL, DEFAULT_APP_URL);
    }

    public String getAppURL() {
        return appURL;
    }

    public void saveAppURL(String newURL) {
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putString(KEY_APP_URL, newURL);
        editor.commit();
        appURL = newURL;
    }
}
