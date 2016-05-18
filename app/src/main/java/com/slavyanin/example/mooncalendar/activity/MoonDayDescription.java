package com.slavyanin.example.mooncalendar.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.slavyanin.example.mooncalendar.R;

public class MoonDayDescription extends AppCompatActivity implements GestureDetector.OnGestureListener{

    private static final String DEBUG_TAG = "Gestures";
    private GestureDetectorCompat mDetector;

//    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moonday_description);

        mDetector = new GestureDetectorCompat(this, this);

        //Adding ActionBar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        TextView fulldescription = (TextView) findViewById(R.id.textView_moonday_full_description);

        final LinearLayout linearSymbol = (LinearLayout) findViewById(R.id.symbol_linearlyout);
        final LinearLayout linearStone = (LinearLayout) findViewById(R.id.stone_linearlyout);

        //Create border to ScrollLayout whit radius corner
        GradientDrawable shapeGradient = new GradientDrawable();
        shapeGradient.setShape(GradientDrawable.RECTANGLE);
        shapeGradient.setColor(Color.TRANSPARENT);
        shapeGradient.setStroke(2, Color.parseColor("#c8e6ff"));
        shapeGradient.setCornerRadius(15.0f);

        linearSymbol.setBackground(shapeGradient);
        linearStone.setBackground(shapeGradient);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
//        getSupportActionBar().setLogo(R.drawable.logo_app1);
        getSupportActionBar().setTitle(R.string.moonDayDescription);
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
                Intent intent = new Intent(MoonDayDescription.this, MainActivity.class);
                startActivity(intent);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event){
        this.mDetector.onTouchEvent(event);
        // Be sure to call the superclass implementation
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent event) {
        Log.d(DEBUG_TAG,"onDown: " + event.toString());
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY) {
        Log.d(DEBUG_TAG, "onFling: " + event1.toString() + event2.toString());

        //Slide to rigth - open FunctionView activity
        if (event1.getX() < event2.getX() && (Math.abs(event2.getX() - event1.getX()) > Math.abs(event2.getY() - event1.getY()))) {
            Intent intent = new Intent(MoonDayDescription.this, MainActivity.class);
            startActivity(intent);
        }
        return true;
    }

}
