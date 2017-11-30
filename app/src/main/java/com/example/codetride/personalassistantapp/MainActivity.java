package com.example.codetride.personalassistantapp;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    TextView name, version, kezna,slogan;
    Intent intent;
    private static final int TAG = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
              intent=new Intent(MainActivity.this,Home.class);
                startActivity(intent);
                finish();
            }
        },TAG);
    }
}
