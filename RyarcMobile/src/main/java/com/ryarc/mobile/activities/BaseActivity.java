package com.ryarc.mobile.activities;

import android.app.Activity;
import android.os.Bundle;

import com.ryarc.mobile.Settings;


public abstract class BaseActivity extends Activity {
    private Settings settings;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        settings = Settings.getInstance(this);
    }

    protected Settings getSettings() {
        return settings;
    }
}
