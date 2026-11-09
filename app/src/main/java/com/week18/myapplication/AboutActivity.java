package com.week18.myapplication;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        Button websiteButton = findViewById(R.id.btnAboutWebsite);
        Button backButton = findViewById(R.id.btnAboutBack);

        websiteButton.setOnClickListener(v -> {
            Intent intent = new Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://www.gmi.edu.my/")
            );

            try {
                startActivity(intent);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(
                        this,
                        "No browser found",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });

        backButton.setOnClickListener(v -> finish());
    }
}
