package com.slavyanin.example.mooncalendar.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.slavyanin.example.mooncalendar.R;

public class ZodiacViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zodiac_view);

        Button buttonZodiacAries = (Button) findViewById(R.id.button_zodiac_aries);

        buttonZodiacAries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ZodiacViewActivity.this, ZodiacDescriptionActivity.class);

                startActivity(intent);
            }
        });
    }
}
