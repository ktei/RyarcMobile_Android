package com.ryarc.mobile.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.util.regex.Matcher;


import com.ryarc.mobile.R;
import com.ryarc.mobile.Settings;

public class ChangeSettings extends BaseActivity {

    private EditText txtAppURL;
    private Button btnSaveChanges;
    private Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_settings);
        init();
    }

    private void init() {
        btnSaveChanges = (Button)findViewById(R.id.save_changes);
        btnSaveChanges.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                saveChanges();
            }
        });

        btnCancel = (Button)findViewById(R.id.cancel);
        btnCancel.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                backToWebApp();
            }
        });

        txtAppURL = (EditText)findViewById(R.id.app_url);
        txtAppURL.setText(getSettings().getAppURL());
    }

    private void saveChanges() {
        String url = txtAppURL.getText().toString();
        if (validateURL(url)) {
            getSettings().saveAppURL(normalizeURL(url));
            backToWebApp();
        }
    }

    private void backToWebApp() {
        Intent intent = new Intent(ChangeSettings.this, WebApp.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private String normalizeURL(String url) {
        if (!(url.startsWith("http://") || url.startsWith("https://"))) {
            return "http://" + url;
        }
        return url;
    }

    private boolean validateURL(String url) {
        Matcher matcher = Patterns.WEB_URL.matcher(url);
        if (!matcher.matches()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(R.string.dlg_invalid_app_url)
                    .setPositiveButton(R.string.dlg_close, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                        }
                    })
            .setTitle(R.string.dlg_general_error_title);
            builder.create().show();
            return false;
        }
        return true;
    }
    
}
