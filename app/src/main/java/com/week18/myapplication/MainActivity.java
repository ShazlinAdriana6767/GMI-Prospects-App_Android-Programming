package com.week18.myapplication;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnDiploma).setOnClickListener(
                v -> openCourses("Diploma")
        );

        findViewById(R.id.btnGapp).setOnClickListener(
                v -> openCourses("GAPP")
        );

        findViewById(R.id.btnGufp).setOnClickListener(
                v -> openCourses("GUFP")
        );

        findViewById(R.id.btnEligibility).setOnClickListener(
                v -> startActivity(
                        new Intent(this, EligibilityActivity.class)
                )
        );

        findViewById(R.id.btnContact).setOnClickListener(
                v -> startActivity(
                        new Intent(this, ContactActivity.class)
                )
        );

        findViewById(R.id.btnAbout).setOnClickListener(
                v -> startActivity(
                        new Intent(this, AboutActivity.class)
                )
        );
    }

    private void openCourses(String category) {
        Intent intent = new Intent(this, CoursesActivity.class);
        intent.putExtra("category", category);
        startActivity(intent);
    }
}