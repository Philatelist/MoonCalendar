package com.slavyanin.example.mooncalendar.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.audiofx.BassBoost;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.slavyanin.example.mooncalendar.adapter.MyAdapter;
import com.slavyanin.example.mooncalendar.R;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    private static final String DEBUG_TAG = "Gestures";
    private Button buttonMoonPhase, buttonZodiac, buttonSettingsMain;
    private GestureDetectorCompat mDetector;

    TextView latitude, longtitude;

    private LocationManager locationManager;


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Forbidden change orientation
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //GPS Location
        latitude = (TextView) findViewById(R.id.tvLatitude);
        longtitude = (TextView) findViewById(R.id.tvLongtitude);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        //Buttons
        buttonMoonPhase = (Button) findViewById(R.id.button_moonPhases);
        buttonZodiac = (Button) findViewById(R.id.button_zodiac);
        buttonSettingsMain = (Button) findViewById(R.id.button_settings_main);

        //Create Gesture detector
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

        buttonSettingsMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settings.ACTION_SETTINGS);

                startActivity(intent);
            }
        });

        //Initialization ScrollView
        final ScrollView shortDescr = (ScrollView) findViewById(R.id.short_descr_main_scroll);

        //Create border to ScrollLayout without radial corner
        ShapeDrawable shape = new ShapeDrawable(new RectShape());
        shape.getPaint().setColor(Color.WHITE);
        shape.getPaint().setStyle(Paint.Style.STROKE);
        shape.getPaint().setStrokeWidth(3);

        //Create border to ScrollLayout whit radius corner
        GradientDrawable shapeGradient = new GradientDrawable();
        shapeGradient.setShape(GradientDrawable.RECTANGLE);
        shapeGradient.setColor(Color.TRANSPARENT);
        shapeGradient.setStroke(2, Color.parseColor("#c8e6ff"));
        shapeGradient.setCornerRadius(15.0f);

        //Initialization ScrollView witn radial border
        shortDescr.setBackground(shapeGradient);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000 * 10, 10, locationListener);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000 * 10, 10, locationListener);
        checkEnabled();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.removeUpdates(locationListener);
    }

    private LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            showLocation(location);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

        @Override
        public void onProviderEnabled(String provider) {
            checkEnabled();
            showLocation(locationManager.getLastKnownLocation(provider));
        }

        @Override
        public void onProviderDisabled(String provider) {
            checkEnabled();
        }
    };

    private void showLocation(Location location) {
        if (location == null) return;
        if (location.getProvider().equals(LocationManager.GPS_PROVIDER)) {
            latitude.setText("Lat: " + formatGPS(location.getLatitude()));
            longtitude.setText("Long: " + formatGPS(location.getLongitude()));
        }
        else if (location.getProvider().equals(LocationManager.NETWORK_PROVIDER)) {
            latitude.setText("Lat: " + formatGPS(location.getLatitude()));
            longtitude.setText("Long: " + formatGPS(location.getLongitude()));
        }
    }

    private void checkEnabled() {
    }

    private String formatGPS (double dLocation) {
        int degree = (int) dLocation;
        int minute = (int) ((dLocation - degree) * 60);
        double second = ((((dLocation - degree) * 60) - minute) * 60);

        return String.format("%d\u00b0%d\'%.2f\"", degree, minute, second);
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


    //Gesture detect
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
