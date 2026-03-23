package com.example.prayaan;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText email, password;
    Button loginBtn, goRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        loginBtn = findViewById(R.id.loginBtn);
        goRegister = findViewById(R.id.goRegister);

        // LOGIN BUTTON
        loginBtn.setOnClickListener(v -> {

            String userEmail = email.getText().toString().trim();
            String userPass = password.getText().toString().trim();

            // 1️⃣ Check empty fields
            if (userEmail.isEmpty() || userPass.isEmpty()) {
                Toast.makeText(MainActivity.this,
                        "Enter all details",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            // 2️⃣ Check valid email format
            if (!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()) {
                Toast.makeText(MainActivity.this,
                        "Invalid email format!",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            // 3️⃣ Check allowed domains
            String[] allowedDomains = {"gmail.com", "icloud.com", ".ac.in"};
            boolean validDomain = false;

            for (String domain : allowedDomains) {
                if (userEmail.endsWith(domain)) {
                    validDomain = true;
                    break;
                }
            }

            if (!validDomain) {
                Toast.makeText(MainActivity.this,
                        "Invalid email domain! Allowed: gmail.com, icloud.com, .ac.in",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            // ✅ All checks passed
            Toast.makeText(MainActivity.this,
                    "Login Successful",
                    Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
            startActivity(intent);

            // Example: Go to some dashboard or next activity after login
            // Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
            // startActivity(intent);
            // finish();
        });

        // GO TO REGISTER
        goRegister.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
    }
}