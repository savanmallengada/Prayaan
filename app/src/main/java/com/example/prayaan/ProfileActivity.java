package com.example.prayaan;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    EditText name, bike, blood, contact, contact2, contact3, language;
    Button saveBtn;
    RadioGroup rideType;
    CheckBox location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        name = findViewById(R.id.name);
        bike = findViewById(R.id.bike);
        blood = findViewById(R.id.blood);
        contact = findViewById(R.id.contact);
        contact2 = findViewById(R.id.contact2);
        contact3 = findViewById(R.id.contact3);
        language = findViewById(R.id.language);
        rideType = findViewById(R.id.rideType);
        location = findViewById(R.id.location);
        saveBtn = findViewById(R.id.saveBtn);

        SharedPreferences sp = getSharedPreferences("ProfileData", MODE_PRIVATE);

        // LOAD DATA
        name.setText(sp.getString("name", ""));
        bike.setText(sp.getString("bike", ""));
        blood.setText(sp.getString("blood", ""));
        contact.setText(sp.getString("contact", ""));
        contact2.setText(sp.getString("contact2", ""));
        contact3.setText(sp.getString("contact3", ""));
        language.setText(sp.getString("language", ""));
        location.setChecked(sp.getBoolean("location", false));

        String ride = sp.getString("rideType", "");
        if (ride.equals("solo")) {
            rideType.check(R.id.solo);
        } else if (ride.equals("group")) {
            rideType.check(R.id.group);
        }

        saveBtn.setOnClickListener(v -> {

            String userName = name.getText().toString();
            String bikeType = bike.getText().toString();
            String bloodGroup = blood.getText().toString();
            String c1 = contact.getText().toString();
            String c2 = contact2.getText().toString();
            String c3 = contact3.getText().toString();
            String lang = language.getText().toString();
            boolean loc = location.isChecked();

            int selectedRide = rideType.getCheckedRadioButtonId();
            String rideChoice = "";

            if (selectedRide == R.id.solo) {
                rideChoice = "solo";
            } else if (selectedRide == R.id.group) {
                rideChoice = "group";
            }

            if (userName.isEmpty() || bikeType.isEmpty() || bloodGroup.isEmpty() || c1.isEmpty()) {

                Toast.makeText(ProfileActivity.this,
                        "Fill required fields",
                        Toast.LENGTH_SHORT).show();

            } else {

                SharedPreferences.Editor editor = sp.edit();

                editor.putString("name", userName);
                editor.putString("bike", bikeType);
                editor.putString("blood", bloodGroup);
                editor.putString("contact", c1);
                editor.putString("contact2", c2);
                editor.putString("contact3", c3);
                editor.putString("language", lang);
                editor.putBoolean("location", loc);
                editor.putString("rideType", rideChoice);

                editor.apply();

                Toast.makeText(ProfileActivity.this,
                        "Profile & Preferences Saved",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}