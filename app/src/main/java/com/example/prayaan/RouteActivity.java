package com.example.prayaan;

import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.IOException;
import java.util.List;

public class RouteActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private TextView tvRouteInfo;
    String start, destination;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route);

        start = getIntent().getStringExtra("start");
        destination = getIntent().getStringExtra("destination");

        SupportMapFragment mapFragment = (SupportMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.map);

        tvRouteInfo = findViewById(R.id.tvRouteInfo);

        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        Geocoder geocoder = new Geocoder(this);

        try {
            List<Address> startList = geocoder.getFromLocationName(start, 1);
            List<Address> destList = geocoder.getFromLocationName(destination, 1);

            if (startList != null && destList != null &&
                    !startList.isEmpty() && !destList.isEmpty()) {

                LatLng startLoc = new LatLng(
                        startList.get(0).getLatitude(),
                        startList.get(0).getLongitude());

                LatLng destLoc = new LatLng(
                        destList.get(0).getLatitude(),
                        destList.get(0).getLongitude());

                mMap.addMarker(new MarkerOptions().position(startLoc).title("Start"));
                mMap.addMarker(new MarkerOptions().position(destLoc).title("Destination"));

                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(startLoc, 7));

                mMap.addPolyline(new PolylineOptions()
                        .add(startLoc, destLoc)
                        .width(8)
                        .color(Color.BLUE));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}