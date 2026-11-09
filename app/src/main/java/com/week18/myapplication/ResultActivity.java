
package com.week18.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    private LinearLayout resultContainer;

    private static final String GMI_URL =
            "https://www.gmi.edu.my/";

    private static final String MARA_URL =
            "https://yp.mara.gov.my/ytpmara/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        resultContainer = findViewById(R.id.resultContainer);

        TextView examResult = findViewById(R.id.txtExamResult);
        Button checkAgain = findViewById(R.id.btnCheckAgain);
        Button homeButton = findViewById(R.id.btnResultHome);

        Intent intent = getIntent();

        String examType = intent.getStringExtra("exam_type");

        boolean diploma = intent.getBooleanExtra("diploma", false);
        boolean gappPrivate =
                intent.getBooleanExtra("gapp_private", false);
        boolean gappMara =
                intent.getBooleanExtra("gapp_mara", false);
        boolean gufp = intent.getBooleanExtra("gufp", false);

        examResult.setText("Examination: " +
                (examType == null ? "Not specified" : examType));

        if ("SPM".equals(examType)) {

            addResultCard(
                    "Diploma Programmes",
                    diploma,
                    "3 Years",
                    GMI_URL
            );

            addResultCard(
                    "GAPP - Private",
                    gappPrivate,
                    "20 Months at GMI + 6 Months in Germany",
                    GMI_URL
            );

            addResultCard(
                    "GAPP - MARA YTP",
                    gappMara,
                    "20 Months at GMI + 6 Months in Germany",
                    MARA_URL
            );

            addResultCard(
                    "German University Foundation Programme (GUFP)",
                    gufp,
                    "1 Year",
                    GMI_URL
            );

        } else if ("IGCSE / O-Level".equals(examType)) {

            addResultCard(
                    "German University Foundation Programme (GUFP)",
                    gufp,
                    "1 Year",
                    GMI_URL
            );

        } else {

            TextView message = new TextView(this);
            message.setText("No examination results available.");
            message.setTextColor(getColor(R.color.gmi_gray));
            resultContainer.addView(message);
        }

        checkAgain.setOnClickListener(v -> finish());

        homeButton.setOnClickListener(v -> {
            Intent homeIntent = new Intent(
                    this, MainActivity.class);

            homeIntent.addFlags(
                    Intent.FLAG_ACTIVITY_CLEAR_TOP
                            | Intent.FLAG_ACTIVITY_SINGLE_TOP);

            startActivity(homeIntent);
            finish();
        });
    }

    private void addResultCard(String programme,
                               boolean eligible,
                               String duration,
                               String website) {

        LinearLayout card = new LinearLayout(this);
        card.setOrientation(LinearLayout.VERTICAL);
        card.setPadding(dp(16), dp(16), dp(16), dp(16));

        GradientDrawable background = new GradientDrawable();
        background.setColor(Color.WHITE);
        background.setCornerRadius(dp(10));
        background.setStroke(dp(1), 0xFFE0E5ED);
        card.setBackground(background);

        LinearLayout.LayoutParams cardParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);

        cardParams.setMargins(0, 0, 0, dp(14));
        resultContainer.addView(card, cardParams);

        TextView title = new TextView(this);
        title.setText(programme);
        title.setTextSize(17);
        title.setTypeface(null, Typeface.BOLD);
        title.setTextColor(getColor(R.color.gmi_blue));
        card.addView(title);

        TextView status = new TextView(this);

        if (eligible) {
            status.setText("Potentially Eligible");
            status.setTextColor(0xFF248447);
        } else {
            status.setText("Requirements Not Met");
            status.setTextColor(getColor(R.color.gmi_red));
        }

        status.setTextSize(15);
        status.setTypeface(null, Typeface.BOLD);
        status.setPadding(0, dp(10), 0, dp(6));
        card.addView(status);

        TextView durationText = new TextView(this);
        durationText.setText("Duration: " + duration);
        durationText.setTextSize(14);
        durationText.setTextColor(getColor(R.color.gmi_gray));
        card.addView(durationText);

        if (eligible) {

            Button applyButton = new Button(this);
            applyButton.setText(
                    website.equals(MARA_URL)
                            ? "Visit MARA Application Portal"
                            : "Visit GMI Website");

            applyButton.setAllCaps(false);
            applyButton.setTextColor(Color.WHITE);
            applyButton.setBackgroundTintList(
                    getColorStateList(R.color.gmi_blue));

            LinearLayout.LayoutParams buttonParams =
                    new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT);

            buttonParams.setMargins(0, dp(12), 0, 0);
            card.addView(applyButton, buttonParams);

            applyButton.setOnClickListener(v -> {
                Intent browser = new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(website));

                if (browser.resolveActivity(
                        getPackageManager()) != null) {
                    startActivity(browser);
                }
            });
        }
    }

    private int dp(int value) {
        return (int) (
                value * getResources()
                        .getDisplayMetrics().density + 0.5f);
    }
}
