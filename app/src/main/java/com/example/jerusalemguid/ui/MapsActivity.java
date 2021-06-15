package com.example.jerusalemguid.ui;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.example.jerusalemguid.R;
import com.example.jerusalemguid.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
       Double lat1 = getIntent().getDoubleExtra("lat1",0.0);
       Double lat2 = getIntent().getDoubleExtra("lat2",0.0);
       String title = getIntent().getStringExtra("title");
        googleMap.getUiSettings().setZoomGesturesEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setZoomGesturesEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);
        LatLng sydney = new LatLng(   lat1, lat2);
        CameraUpdate location = CameraUpdateFactory.newLatLngZoom(
                sydney, 15);
        mMap.animateCamera(location);
        float zoomLevel = 16.0f;
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, zoomLevel));
        mMap.addMarker(new MarkerOptions().position(sydney).title(title));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}