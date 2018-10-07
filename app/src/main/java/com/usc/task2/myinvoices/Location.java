package com.usc.task2.myinvoices;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Location extends AppCompatActivity {

    WebView web2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_location);

        web2 = findViewById(R.id.webview2);
        web2.getSettings().setJavaScriptEnabled(true);
        web2.setWebViewClient(new WebViewClient());
        web2.loadUrl("https://www.google.com/map");

    }

    public static Intent newIntent(Context packageContent){

        Intent intent = new Intent(packageContent, Location.class);
        return intent;

    }

}
