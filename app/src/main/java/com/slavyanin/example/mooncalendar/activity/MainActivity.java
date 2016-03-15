package com.slavyanin.example.mooncalendar.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.slavyanin.example.mooncalendar.R;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener{

    private static final String DEBUG_TAG = "Gestures";
    private Button buttonMoonPhase;
    private Button buttonZodiac;
    private GestureDetectorCompat mDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonMoonPhase = (Button) findViewById(R.id.button_moonPhases);
        buttonZodiac = (Button) findViewById(R.id.button_zodiac);

        mDetector = new GestureDetectorCompat(this, this);
        mDetector.setOnDoubleTapListener(this);

        buttonMoonPhase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MoonPhasesViewActivity.class);

                startActivity(intent);
            }
        });

        buttonZodiac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ZodiacViewActivity.class);

                startActivity(intent);
            }
        });


    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event){
//
//        int action = MotionEventCompat.getActionMasked(event);
//
//        switch(action) {
//            case (MotionEvent.ACTION_DOWN) :
//                Log.d(DEBUG_TAG,"Action was DOWN");
//                return true;
//            case (MotionEvent.ACTION_MOVE) :
//                Log.d(DEBUG_TAG,"Action was MOVE");
//                return true;
//            case (MotionEvent.ACTION_UP) :
//                Log.d(DEBUG_TAG,"Action was UP");
//                return true;
//            case (MotionEvent.ACTION_CANCEL) :
//                Log.d(DEBUG_TAG,"Action was CANCEL");
//                return true;
//            case (MotionEvent.ACTION_OUTSIDE) :
//                Log.d(DEBUG_TAG,"Movement occurred outside bounds " +
//                        "of current screen element");
//                return true;
//            default :
//                return super.onTouchEvent(event);
//        }
//    }

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
    public boolean onFling(MotionEvent event1, MotionEvent event2,
                           float velocityX, float velocityY) {
        Log.d(DEBUG_TAG, "onFling: " + event1.toString()+event2.toString());

        //Slide to rigth - open FunctionView activity
        if (event1.getX() < event2.getX() && (Math.abs(event2.getX() - event1.getX()) > Math.abs(event2.getY() - event1.getY()))) {
            Intent intent = new Intent(MainActivity.this, FunctionViewActivity.class);
            startActivity(intent);
        }

        //Slide to left - open MoonDayDescription activity
        if (event1.getX() > event2.getX() && (Math.abs(event2.getX() - event1.getX()) > Math.abs(event2.getY() - event1.getY()))) {
            Intent intent = new Intent(MainActivity.this, MoonDayDescription.class);
            startActivity(intent);
        }

        //Slide to up - open MainActivity for Next Moonday
        if (event1.getY() > event2.getY() && (Math.abs(event2.getX() - event1.getX()) < Math.abs(event2.getY() - event1.getY()))) {
            Intent intent = new Intent(MainActivity.this, MainActivity.class);
            startActivity(intent);
        }

        //Slide to up - open MainActivity for Previous Moonday
        if (event1.getY() < event2.getY() && (Math.abs(event2.getX() - event1.getX()) < Math.abs(event2.getY() - event1.getY()))) {
            Intent intent = new Intent(MainActivity.this, MainActivity.class);
            startActivity(intent);
        }

        return true;
    }

    @Override
    public void onLongPress(MotionEvent event) {
        Log.d(DEBUG_TAG, "onLongPress: " + event.toString());
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
                            float distanceY) {
        Log.d(DEBUG_TAG, "onScroll: " + e1.toString()+e2.toString());

        return true;
    }

    @Override
    public void onShowPress(MotionEvent event) {
        Log.d(DEBUG_TAG, "onShowPress: " + event.toString());
    }

    @Override
    public boolean onSingleTapUp(MotionEvent event) {
        Log.d(DEBUG_TAG, "onSingleTapUp: " + event.toString());
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent event) {
        Log.d(DEBUG_TAG, "onDoubleTap: " + event.toString());
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent event) {
        Log.d(DEBUG_TAG, "onDoubleTapEvent: " + event.toString());
        return true;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent event) {
        Log.d(DEBUG_TAG, "onSingleTapConfirmed: " + event.toString());
        return true;
    }
}
