package com.example.prayaan;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    EditText name, email, password;
    Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // connect XML to Java
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        registerBtn = findViewById(R.id.registerBtn);

        // button click
        registerBtn.setOnClickListener(v -> {

            String userName = name.getText().toString().trim();
            String userEmail = email.getText().toString().trim();
            String userPass = password.getText().toString().trim();

            // validation for empty fields
            if (userName.isEmpty() || userEmail.isEmpty() || userPass.isEmpty()) {
                Toast.makeText(RegisterActivity.this,
                        "Please fill all fields",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            // allowed email domains
            String[] allowedDomains = {"gmail.com", "icloud.com", ".ac.in"};
            boolean validDomain = false;

            for (String domain : allowedDomains) {
                if (userEmail.endsWith(domain)) {
                    validDomain = true;
                    break;
                }
            }

            if (!validDomain) {
                Toast.makeText(RegisterActivity.this,
                        "Invalid email! Allowed domains: gmail.com, icloud.com, .ac.in",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            // if everything is okay
            Toast.makeText(RegisterActivity.this,
                    "Registered Successfully",
                    Toast.LENGTH_SHORT).show();

            // go back to login screen
            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}