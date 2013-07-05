package com.ryarc.mobile.activities;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.ryarc.mobile.R;
import com.ryarc.mobile.Settings;

public class WebApp extends Activity {

    private static final int MENU_SETTINGS = Menu.FIRST;

    private WebView browser;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, WebApp.MENU_SETTINGS, 0,
                R.string.action_settings).setIcon(
                android.R.drawable.ic_menu_more);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_app);
        this.init();
    }

    private void init() {
        Settings.Load();
        browser = (WebView)findViewById(R.id.browser);
        browser.getSettings().setJavaScriptEnabled(true);
        browser.setWebViewClient(new AppWebViewClient());
        browser.loadUrl(Settings.getAppURL());
    }

    private class AppWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
    
}
