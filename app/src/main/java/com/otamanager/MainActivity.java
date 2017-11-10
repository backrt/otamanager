package com.otamanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jettyserver.server.WebService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onStartClick();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        onStopClick();
    }

    public void onStartClick() {
        Intent intent = new Intent(this, WebService.class);
        startService(intent);
    }

    public void onStopClick() {
        Intent intent = new Intent(this, WebService.class);
        stopService(intent);
    }
}
