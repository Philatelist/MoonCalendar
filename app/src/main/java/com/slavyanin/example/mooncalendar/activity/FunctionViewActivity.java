package com.slavyanin.example.mooncalendar.activity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.slavyanin.example.mooncalendar.R;

public class FunctionViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_function_view);

        Button impactIndividual = (Button) findViewById(R.id.button_functionlImpactIndividual);

        impactIndividual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FunctionViewActivity.this, FunctionDescriptionActivity.class);

                startActivity(intent);
            }
        });

        //Adding ActionBar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        getSupportActionBar().setLogo(R.drawable.logo_app1);
        getSupportActionBar().setTitle(R.string.functionview);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent settingsIntent = new Intent(Settings.ACTION_SETTINGS);
                startActivity(settingsIntent);
                return true;

            case R.id.action_share:
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, R.string.share_subject);
                shareIntent.putExtra(Intent.EXTRA_TEXT, "TEXT"); // doesn`t work -- shareIntent.putExtra(Intent.EXTRA_TEXT, R.string.share_text);
                startActivity(Intent.createChooser(shareIntent, "Hello")); //doesn`t work -- startActivity(Intent.createChooser(shareIntent, R.string.share_title));
                return true;

            case R.id.action_home:
                Intent intent = new Intent(FunctionViewActivity.this, MainActivity.class);
                startActivity(intent);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
