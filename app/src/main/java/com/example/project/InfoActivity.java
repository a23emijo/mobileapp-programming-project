package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class InfoActivity extends AppCompatActivity {
    private TextView infoText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_activity);
        infoText = findViewById(R.id.infoText); // Finds the infoText TextView

        // Gets the intents
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String auxInfo = extras.getString("auxInfo");
            infoText.setText(auxInfo); // Sets the text to the vehicle inputted in MainActivity
        }
    }
}
