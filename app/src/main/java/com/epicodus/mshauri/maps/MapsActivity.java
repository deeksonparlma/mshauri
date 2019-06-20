package com.epicodus.mshauri.maps;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.sip.SipSession;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.mshauri.R;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,LocationListener {
    private double[] latitudes={-1.2177,-1.2283,-1.2351,-1.2483,-1.3583};
    private double[] longitudes={36.1977,36.911,36.8981,36.711,39.611};
    private String[] tags={"GBV CENTER","GBV CENTER","GBV CENTER","GBV CENTER","GBV CENTER"};
    private GoogleMap mMap;
    LocationManager locationManager;
    @BindView(R.id.locationpoints)
    TextView mPoint;
    Location mLatest;
    String lattitude, longitude;
    // The minimum distance to change Updates in meters
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 1; // 10 meters

    // The minimum time between updates in milliseconds
    private static final long MIN_TIME_BW_UPDATES = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_maps);
        ButterKnife.bind(this);
        ActivityCompat.requestPermissions(MapsActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        for(Double lat:latitudes){
            for(Double longit : longitudes){
                for(String tag:tags){
                    LatLng place = new LatLng(lat,longit);
                    mMap.addMarker(new MarkerOptions().position(place).title(tag));
                }
            }
        }
//         Add a marker in Sydney and move the camera
        LatLng Coptic = new LatLng(-1.2977 , 36.7977);
        mMap.addMarker(new MarkerOptions().position(Coptic).title("Coptic GBV center"));

        LatLng wanjuKanja = new LatLng(-1.2683, 36.711);
        mMap.addMarker(new MarkerOptions().position(wanjuKanja).title("Wangu Kanja"));

        LatLng NairobiWomen = new LatLng(-1.2951, 36.7981);
        mMap.addMarker(new MarkerOptions().position(NairobiWomen).title("Nairobi Women's GBV Center"));

        LatLng meru = new LatLng(-1.2183, 36.811);
        mMap.addMarker(new MarkerOptions().position(meru).title("Kitisuru GBV center"));

        LatLng coast = new LatLng(-1.3683, 39.711);
        mMap.addMarker(new MarkerOptions().position(coast).title("Coast GBV"));


        mMap.moveCamera(CameraUpdateFactory.newLatLng(Coptic));
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(10);
        mMap.animateCamera(zoom);

        if ((ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)) {
            mMap.setMyLocationEnabled(true);
            LatLng here = new LatLng(-1.23,37.8);
            mMap.moveCamera(CameraUpdateFactory.newLatLng(here));
            CameraUpdate zoomm = CameraUpdateFactory.zoomTo(6);
            mMap.animateCamera(zoomm);
        }


    }

    @Override
    public void onLocationChanged(Location location) {
//        mLatest = location;
        Double lat=location.getLatitude();
        Log.d("lat", String.valueOf(lat));
        Double longitude=location.getLongitude();
        System.out.println("hhhhhhhhhhhhhhhhhhh"+longitude);
        Log.d("longitudeeee",String.valueOf(location.getLongitude()));
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
