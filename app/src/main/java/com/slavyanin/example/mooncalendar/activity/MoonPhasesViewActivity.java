package com.slavyanin.example.mooncalendar.activity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.slavyanin.example.mooncalendar.R;
import com.slavyanin.example.mooncalendar.adapter.PhaseAdapter;
import com.slavyanin.example.mooncalendar.entity.MoonPhase;

import java.util.ArrayList;

public class MoonPhasesViewActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((R.layout.activity_moonphases_view));

        //Adding ActionBar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        //Add listview with entity, with adapter
        ListView listPhase = (ListView) findViewById(R.id.listview_phase);

        ArrayList<MoonPhase> phases = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            MoonPhase phaseObj = new MoonPhase();
            phases.add(phaseObj);
        }

        PhaseAdapter adapter = new PhaseAdapter(this, R.layout.list_phase, phases);
        listPhase.setAdapter(adapter);

        listPhase.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MoonPhasesViewActivity.this, MoonPhasesDescriptionActivity.class);
                intent.putExtra("key", position);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
//        getSupportActionBar().setLogo(R.drawable.logo_app1);
        getSupportActionBar().setTitle(R.string.moonPhaseView);
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
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.share_subject)); //Проверить работоспособность getString
                shareIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_text));
                startActivity(Intent.createChooser(shareIntent, "Hello")); //doesn`t work -- startActivity(Intent.createChooser(shareIntent, R.string.share_title));
                return true;

            case R.id.action_home:
                Intent intent = new Intent(MoonPhasesViewActivity.this, MainActivity.class);
                startActivity(intent);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
