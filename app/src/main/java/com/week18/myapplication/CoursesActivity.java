
package com.week18.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CoursesActivity extends AppCompatActivity {

    private LinearLayout courseContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        TextView title = findViewById(R.id.txtCourseTitle);
        TextView description = findViewById(R.id.txtCourseDescription);
        courseContainer = findViewById(R.id.courseContainer);

        String category = getIntent().getStringExtra("category");

        if ("Diploma".equals(category)) {
            title.setText(R.string.diploma);
            description.setText(R.string.programmes_description);
            showDiploma();
        } else if ("GAPP".equals(category)) {
            title.setText(R.string.gapp);
            description.setText(R.string.programmes_description);
            addCourse("German A-Level Preparatory Programme",
                    getString(R.string.gapp_duration),
                    getString(R.string.gapp_fees));
        } else if ("GUFP".equals(category)) {
            title.setText(R.string.gufp);
            description.setText(R.string.programmes_description);
            addCourse("Foundation Programme",
                    getString(R.string.gufp_duration),
                    getString(R.string.gufp_fees));
        }
    }

    private void showDiploma() {
        String[] courses = {
                "Mechatronics Engineering Technology",
                "Electronics Engineering Technology (Computer)",
                "Engineering Technology (Instrumentation and Control)",
                "Engineering Technology (Sustainable Energy and Power Distribution)",
                "Autotronics Engineering Technology",
                "Mechanical Engineering Technology (Manufacturing)",
                "Engineering Technology (Machine Tools Maintenance)",
                "Innovative Product Design Engineering Technology",
                "Precision Tooling Engineering Technology",
                "Industrial Quality Engineering Technology",
                "Software Engineering",
                "Creative Multimedia"
        };

        for (String course : courses) {
            addCourse(course,
                    getString(R.string.diploma_duration),
                    getString(R.string.diploma_fees));
        }
    }

    private void addCourse(String name, String duration, String fees) {

        LinearLayout card = new LinearLayout(this);
        card.setOrientation(LinearLayout.VERTICAL);
        card.setPadding(20, 20, 20, 20);
        card.setBackgroundColor(Color.WHITE);

        LinearLayout.LayoutParams cardParams =
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);

        cardParams.setMargins(0, 0, 0, 18);
        courseContainer.addView(card, cardParams);

        TextView courseName = new TextView(this);
        courseName.setText(name);
        courseName.setTextSize(18);
        courseName.setTextColor(
                getResources().getColor(R.color.gmi_blue, getTheme()));
        courseName.setTypeface(null, 1);
        card.addView(courseName);

        TextView details = new TextView(this);
        details.setText(getString(R.string.course_duration) + ": " + duration
                + "\n" + getString(R.string.course_fees) + ": " + fees);
        details.setTextSize(14);
        details.setPadding(0, 12, 0, 12);
        card.addView(details);

        Button button = new Button(this);
        button.setText(R.string.view_details);
        button.setAllCaps(false);
        button.setBackgroundTintList(
                getColorStateList(R.color.gmi_blue));
        button.setTextColor(Color.WHITE);

        button.setOnClickListener(v -> {
            Intent intent = new Intent(
                    CoursesActivity.this,
                    CourseDetailActivity.class);

            intent.putExtra("course_name", name);
            intent.putExtra("course_duration", duration);
            intent.putExtra("course_fees", fees);

            startActivity(intent);
        });

        card.addView(button);
    }
}
