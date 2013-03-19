package edu.elon.locationexposure;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class LocationExposureActivity extends Activity {

    private TextView textView;
    private RelativeLayout relativeLayout;
    private LocationManager locManager;

    private int[] colors = { Color.RED, Color.GREEN };
    private int which;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_exposure);

        textView = (TextView) findViewById(R.id.textview);
        relativeLayout = (RelativeLayout) findViewById(R.id.relativelayout);

        which = -1;
    }

    @Override
    protected void onResume() {

        super.onResume();
        locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
    }

    @Override
    protected void onPause() {

        super.onPause();

        locManager.removeUpdates(locationListener);
    }

    private LocationListener locationListener = new LocationListener() {

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

            // FIXME Auto-generated method stub

        }

        @Override
        public void onProviderEnabled(String provider) {

            // FIXME Auto-generated method stub

        }

        @Override
        public void onProviderDisabled(String provider) {

            // FIXME Auto-generated method stub

        }

        @Override
        public void onLocationChanged(Location location) {

            double currentLatitude = location.getLatitude();
            double currentLongitude = location.getLongitude();

            textView.setText("Latitude: " + currentLatitude + "\n"
                                            + "Longitude: " + currentLongitude);

            which = (which + 1) % colors.length;

            relativeLayout.setBackgroundColor(colors[which]);
        }
    };
}