package com.example.juheee.myroad_final;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends Activity {
    private TimerTask mTask;
    private Timer mTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(android.R.style.Theme_NoTitleBar_Fullscreen);
        setContentView(R.layout.activity_splash);

        mTask = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplication(), MainActivity.class);
                startActivity(intent);
                finish();

            }
        };

        mTimer = new Timer();
        mTimer.schedule(mTask, 1000);
    }

    @Override
    protected void onDestroy() {
        mTimer.cancel();
        super.onDestroy();
    }
}
