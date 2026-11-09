
package com.week18.myapplication;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        Button mapsButton = findViewById(R.id.btnOpenMaps);
        Button callButton = findViewById(R.id.btnCallGmi);
        Button emailButton = findViewById(R.id.btnEmailGmi);
        Button websiteButton = findViewById(R.id.btnWebsiteGmi);
        Button backButton = findViewById(R.id.btnContactBack);

        mapsButton.setOnClickListener(v -> {
            Uri location = Uri.parse(
                    "https://www.google.com/maps/search/?api=1&query=German-Malaysian+Institute+Kajang");
            openLink(location);
        });

        callButton.setOnClickListener(v -> {
            Intent callIntent = new Intent(
                    Intent.ACTION_DIAL,
                    Uri.parse("tel:+60389219000")
            );
            launchIntent(callIntent);
        });

        emailButton.setOnClickListener(v -> {
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
            emailIntent.setData(
                    Uri.parse("mailto:marketing@gmi.edu.my")
            );
            emailIntent.putExtra(
                    Intent.EXTRA_SUBJECT,
                    "GMI Admission Enquiry"
            );
            launchIntent(emailIntent);
        });

        websiteButton.setOnClickListener(v -> {
            openLink(Uri.parse("https://www.gmi.edu.my/"));
        });

        backButton.setOnClickListener(v -> finish());
    }

    private void openLink(Uri uri) {
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        launchIntent(intent);
    }

    private void launchIntent(Intent intent) {
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(
                    this,
                    "No compatible app found",
                    Toast.LENGTH_SHORT
            ).show();
        }
    }
}
