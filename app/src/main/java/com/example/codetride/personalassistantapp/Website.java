package com.example.codetride.personalassistantapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class Website extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_website);

        webView = (WebView) findViewById(R.id.webview);

        webView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = webView.getSettings();

        webSettings.setLoadsImagesAutomatically(true);
        webSettings.setJavaScriptEnabled(true);

        //webView.loadUrl("https://www.google.com");

        Intent intent = getIntent();
        String name = intent.getStringExtra("Search");

                //for search information using lo
                webView.loadUrl("https://en.m.wikipedia.org/wiki/"+ name);
    }
}
