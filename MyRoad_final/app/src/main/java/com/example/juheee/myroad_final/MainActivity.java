package com.example.juheee.myroad_final;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnMap;
    Button btnFood;
    Button btnTrip;
    Button btnSleep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMap = (Button) findViewById(R.id.btnMap);
        btnFood = (Button) findViewById(R.id.btnFood);
        btnTrip = (Button) findViewById(R.id.btnTrip);
        btnSleep = (Button) findViewById(R.id.btnSleep);

        btnMap.setOnClickListener(new View.OnClickListener() {
                @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://map.daum.net/"));
                startActivity(intent);
            }
        });

        btnFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FoodActivity.class);
                startActivity(intent);
            }
        });

        btnTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TripActivity.class);
                startActivity(intent);
            }
        });

        btnSleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SleepActivity.class);
                startActivity(intent);
            }
        });
    }
}
