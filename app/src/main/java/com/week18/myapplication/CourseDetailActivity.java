
package com.week18.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CourseDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);

        TextView name = findViewById(R.id.txtDetailName);
        TextView duration = findViewById(R.id.txtDetailDuration);
        TextView fees = findViewById(R.id.txtDetailFees);
        TextView description = findViewById(R.id.txtDetailDescription);

        Button eligibilityButton =
                findViewById(R.id.btnCheckCourseEligibility);

        Button backButton =
                findViewById(R.id.btnBackCourses);

        String courseName = getIntent().getStringExtra("course_name");
        String courseDuration = getIntent().getStringExtra("course_duration");
        String courseFees = getIntent().getStringExtra("course_fees");

        if (courseName == null) {
            courseName = getString(R.string.programmes_title);
        }

        if (courseDuration == null) {
            courseDuration = "-";
        }

        if (courseFees == null) {
            courseFees = "-";
        }

        name.setText(courseName);

        duration.setText(
                getString(R.string.course_duration_value, courseDuration)
        );

        fees.setText(
                getString(R.string.course_fees_value, courseFees)
        );

        if (courseName.contains("German A-Level")) {
            description.setText(R.string.gapp_description);

        } else if (courseName.contains("Foundation")) {
            description.setText(R.string.gufp_description);

        } else {
            description.setText(R.string.diploma_description);
        }

        eligibilityButton.setOnClickListener(v -> {
            Intent intent = new Intent(
                    CourseDetailActivity.this,
                    EligibilityActivity.class
            );

            startActivity(intent);
        });

        backButton.setOnClickListener(v -> finish());
    }
}
