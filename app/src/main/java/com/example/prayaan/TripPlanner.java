package com.example.prayaan;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class TripPlanner extends AppCompatActivity {

    EditText etStart, etDestination;
    Spinner spRouteType;
    Button btnPlanTrip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_planner);

        etStart = findViewById(R.id.etStart);
        etDestination = findViewById(R.id.etDestination);
        spRouteType = findViewById(R.id.spRouteType);
        btnPlanTrip = findViewById(R.id.btnPlanTrip);

        String[] routes = {"Fastest", "Safest"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, routes);
        spRouteType.setAdapter(adapter);

        btnPlanTrip.setOnClickListener(v -> {
            String start = etStart.getText().toString();
            String dest = etDestination.getText().toString();
            String type = spRouteType.getSelectedItem().toString();

            Intent intent = new Intent(this, RouteActivity.class);
            intent.putExtra("start", start);
            intent.putExtra("destination", dest);
            intent.putExtra("type", type);
            startActivity(intent);
        });
    }
}
