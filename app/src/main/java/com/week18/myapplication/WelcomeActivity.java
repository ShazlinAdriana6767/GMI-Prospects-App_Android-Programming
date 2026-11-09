
package com.week18.myapplication;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_welcome);

        findViewById(R.id.btnGetStarted).setOnClickListener(v -> {
            Intent intent = new Intent(
                    WelcomeActivity.this,
                    MainActivity.class
            );

            startActivity(intent);
            finish();
        });
    }
}
