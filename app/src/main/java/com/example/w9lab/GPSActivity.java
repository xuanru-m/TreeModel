package com.example.w9lab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class GPSActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);

        LocationListener listener = new LocationListener() {
            public void onLocationChanged(Location location) {
                TextView tv = (TextView) findViewById(R.id.locationLabel);
                tv.setText("location=" + location.getLatitude() + "; " + "time=" + location.getTime() + "; " +"Accuracy=" + location.getAccuracy() + "\n");}
            public void onStatusChanged(String provider, int status, Bundle extras) {

                Log.i("LocationTester", "provider status is changed");
            }
            public void onProviderEnabled(String provider) {
                Log.i("LocationTester", "provider is enabled");
            }
            public void onProviderDisabled(String provider) {
                Log.i("LocationTester", "provider is closed");
            }
        };
        RadioGroup methodChoice=findViewById(R.id.locationMethod);
        RadioButton radio1=findViewById(R.id.nwBasedBtn);
        RadioButton radio2=findViewById(R.id.gpsBasedBtn);
        RadioButton radio3=findViewById(R.id.noLocation);
        final LocationManager locationManager=(LocationManager)
                GPSActivity.this.getSystemService(Context.LOCATION_SERVICE);

        methodChoice.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i==radio1.getId()){
                    try{
                        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,0,0,listener);
                    }catch(SecurityException e){ Log.i("network: ","no security permission");}
                }else if(i==radio2.getId()){
                    try{
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,listener);
                    }catch(SecurityException e){Log.i("network: ","no security permission2"); }
                }else{
                    locationManager.removeUpdates(listener);
                    TextView tv=(TextView) findViewById(R.id.locationLabel);
                    tv.setText("location service off");
                }
            }
        });
    }



}