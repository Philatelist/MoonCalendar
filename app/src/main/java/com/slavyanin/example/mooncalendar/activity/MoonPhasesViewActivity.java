package com.slavyanin.example.mooncalendar.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.slavyanin.example.mooncalendar.R;

public class MoonPhasesViewActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((R.layout.activity_moonphases_view));

        Button buttonPhaseNewMoon = (Button) findViewById(R.id.button_phase_new_moon);

        buttonPhaseNewMoon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MoonPhasesViewActivity.this, MoonPhasesDescriptionActivity.class);
                startActivity(intent);
            }
        });
    }
}
