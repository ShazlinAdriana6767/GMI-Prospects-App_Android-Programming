
package com.week18.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class EligibilityActivity extends AppCompatActivity {

    private Spinner examSpinner;
    private LinearLayout gradeContainer;
    private LinearLayout additionalSubjectContainer;

    private final HashMap<String, Spinner> gradeInputs = new HashMap<>();
    private final ArrayList<ExtraSubject> extraSubjects = new ArrayList<>();

    private final String[] spmSubjects = {
            "Bahasa Melayu", "English", "Mathematics",
            "Additional Mathematics", "Physics", "Chemistry"
    };

    private final String[] igcseSubjects = {
            "Mathematics", "Additional Mathematics",
            "Physics", "Chemistry"
    };

    private static class ExtraSubject {
        EditText name;
        Spinner grade;

        ExtraSubject(EditText name, Spinner grade) {
            this.name = name;
            this.grade = grade;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eligibility);

        examSpinner = findViewById(R.id.spinnerExamType);
        gradeContainer = findViewById(R.id.gradeContainer);
        additionalSubjectContainer =
                findViewById(R.id.additionalSubjectContainer);

        Button addButton = findViewById(R.id.btnAddSubject);
        Button checkButton = findViewById(R.id.btnCheckEligibility);

        examSpinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(
                            AdapterView<?> parent,
                            View view,
                            int position,
                            long id) {
                        createGradeInputs(position == 0);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });

        addButton.setOnClickListener(v -> addExtraSubject());

        checkButton.setOnClickListener(v -> checkGrades());
    }

    private Spinner createGradeSpinner(boolean isSpm) {
        Spinner spinner = new Spinner(this);
        spinner.setBackgroundResource(
                android.R.drawable.btn_dropdown);

        int gradeArray = isSpm
                ? R.array.spm_grades
                : R.array.igcse_grades;

        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(
                        this,
                        gradeArray,
                        android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        return spinner;
    }

    private void createGradeInputs(boolean isSpm) {
        gradeContainer.removeAllViews();
        gradeInputs.clear();

        additionalSubjectContainer.removeAllViews();
        extraSubjects.clear();

        String[] subjects = isSpm ? spmSubjects : igcseSubjects;

        for (String subject : subjects) {
            TextView label = new TextView(this);
            label.setText(subject);
            label.setTextSize(15);
            label.setTextColor(getColor(R.color.gmi_blue));
            label.setPadding(0, dp(16), 0, dp(6));
            gradeContainer.addView(label);

            Spinner spinner = createGradeSpinner(isSpm);

            gradeContainer.addView(spinner,
                    new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            dp(52)));

            gradeInputs.put(subject, spinner);
        }
    }

    private void addExtraSubject() {
        boolean isSpm = examSpinner.getSelectedItemPosition() == 0;

        LinearLayout row = new LinearLayout(this);
        row.setOrientation(LinearLayout.VERTICAL);
        row.setPadding(dp(12), dp(12), dp(12), dp(12));
        row.setBackgroundColor(getColor(R.color.gmi_white));

        LinearLayout.LayoutParams rowParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);

        rowParams.setMargins(0, dp(12), 0, 0);
        additionalSubjectContainer.addView(row, rowParams);

        EditText nameInput = new EditText(this);
        nameInput.setHint("Enter subject name");
        nameInput.setTextSize(15);
        nameInput.setSingleLine(true);
        row.addView(nameInput);

        Spinner gradeSpinner = createGradeSpinner(isSpm);
        row.addView(gradeSpinner,
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        dp(52)));

        Button removeButton = new Button(this);
        removeButton.setText("Remove Subject");
        removeButton.setAllCaps(false);
        removeButton.setBackgroundTintList(
                getColorStateList(R.color.gmi_red));
        removeButton.setTextColor(getColor(R.color.gmi_white));
        row.addView(removeButton);

        ExtraSubject extra = new ExtraSubject(nameInput, gradeSpinner);
        extraSubjects.add(extra);

        removeButton.setOnClickListener(v -> {
            extraSubjects.remove(extra);
            additionalSubjectContainer.removeView(row);
        });
    }

    private void checkGrades() {
        HashMap<String, String> grades = new HashMap<>();

        for (String subject : gradeInputs.keySet()) {
            Spinner spinner = gradeInputs.get(subject);
            if (spinner == null) continue;

            String grade = spinner.getSelectedItem().toString();

            if (grade.equals("Select Grade")) {
                Toast.makeText(this,
                        "Please select all required grades",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            grades.put(subject, grade);
        }

        HashSet<String> subjectNames = new HashSet<>(gradeInputs.keySet());

        for (ExtraSubject extra : extraSubjects) {
            String name = extra.name.getText().toString().trim();
            String grade = extra.grade.getSelectedItem().toString();

            if (name.isEmpty()) {
                extra.name.setError("Enter subject name");
                extra.name.requestFocus();
                return;
            }

            String normalizedName = name.toLowerCase();

            boolean duplicate = false;
            for (String existing : subjectNames) {
                if (existing.equalsIgnoreCase(name)) {
                    duplicate = true;
                    break;
                }
            }

            if (duplicate) {
                extra.name.setError("Subject already entered");
                extra.name.requestFocus();
                return;
            }

            if (grade.equals("Select Grade")) {
                Toast.makeText(this,
                        "Please select a grade for " + name,
                        Toast.LENGTH_SHORT).show();
                return;
            }

            grades.put(name, grade);
            subjectNames.add(name);
        }

        boolean isSpm = examSpinner.getSelectedItemPosition() == 0;

        boolean diploma = false;
        boolean gappPrivate = false;
        boolean gappMara = false;
        boolean gufp = false;

        if (isSpm) {
            int credits = 0;
            int aCount = 0;

            for (String grade : grades.values()) {
                if (isCredit(grade)) credits++;
                if (isA(grade)) aCount++;
            }

            boolean scienceCredit =
                    isCredit(grades.get("Physics"))
                            || isCredit(grades.get("Chemistry"));

            diploma = credits >= 3
                    && isCredit(grades.get("Mathematics"))
                    && isPass(grades.get("Bahasa Melayu"))
                    && isPass(grades.get("English"))
                    && scienceCredit;

            gappPrivate =
                    isCredit(grades.get("English"))
                            && isCredit(grades.get("Mathematics"))
                            && isCredit(grades.get("Additional Mathematics"))
                            && isCredit(grades.get("Physics"))
                            && isCredit(grades.get("Chemistry"));

            gappMara = aCount >= 8
                    && isA(grades.get("English"))
                    && isA(grades.get("Additional Mathematics"))
                    && isA(grades.get("Physics"))
                    && isA(grades.get("Chemistry"));

            gufp =
                    isCredit(grades.get("Bahasa Melayu"))
                            && isCredit(grades.get("English"))
                            && isCredit(grades.get("Mathematics"))
                            && isCredit(grades.get("Additional Mathematics"))
                            && isCredit(grades.get("Physics"))
                            && isCredit(grades.get("Chemistry"));

        } else {
            int extraCredits = 0;

            for (ExtraSubject extra : extraSubjects) {
                String grade = extra.grade.getSelectedItem().toString();
                if (isCredit(grade)) extraCredits++;
            }

            gufp =
                    isCredit(grades.get("Mathematics"))
                            && isCredit(grades.get("Additional Mathematics"))
                            && isCredit(grades.get("Physics"))
                            && isCredit(grades.get("Chemistry"))
                            && extraCredits >= 1;
        }

        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("diploma", diploma);
        intent.putExtra("gapp_private", gappPrivate);
        intent.putExtra("gapp_mara", gappMara);
        intent.putExtra("gufp", gufp);
        intent.putExtra("exam_type",
                isSpm ? "SPM" : "IGCSE / O-Level");

        startActivity(intent);
    }

    private int dp(int value) {
        return (int) (value *
                getResources().getDisplayMetrics().density + 0.5f);
    }

    private boolean isA(String grade) {
        return "A+".equals(grade)
                || "A".equals(grade)
                || "A-".equals(grade)
                || "A*".equals(grade);
    }

    private boolean isCredit(String grade) {
        return isA(grade)
                || "B+".equals(grade)
                || "B".equals(grade)
                || "C+".equals(grade)
                || "C".equals(grade);
    }

    private boolean isPass(String grade) {
        return isCredit(grade)
                || "D".equals(grade)
                || "E".equals(grade);
    }
}
