package com.example.prayaan;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RouteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route);

        String start = getIntent().getStringExtra("start");
        String destination = getIntent().getStringExtra("destination");
        String type = getIntent().getStringExtra("type");

        TextView tvRouteInfo = findViewById(R.id.tvRouteInfo);
        String info = "From: " + start + "\nTo: " + destination + "\nRoute Type: " + type;
        tvRouteInfo.setText(info);
    }
}
