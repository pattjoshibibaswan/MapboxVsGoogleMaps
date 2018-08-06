package com.example.bibaswan.sampleapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mapbox.mapboxsdk.overlay.Icon;
import com.mapbox.mapboxsdk.overlay.Marker;
import com.mapbox.mapboxsdk.tileprovider.tilesource.MapboxTileLayer;
import com.mapbox.mapboxsdk.views.MapView;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {
    public static final String LATITIDE = "latitude";
    public static final String LONGITUDE = "longitude";
    private double latitude, longitude;

    private GoogleMap mMap;

    private MapView mapboxMapView;
    public static final String DG_SATELLITE = "digitalglobe.nal0g75k";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        latitude = Double.parseDouble(getIntent().getStringExtra(LATITIDE));
        longitude = Double.parseDouble(getIntent().getStringExtra(LONGITUDE));
        addMarkerGoogleMap(latitude, longitude);
        addMarkerMapBox(latitude, longitude);
    }

    private void addMarkerGoogleMap(double latitude, double longitude) {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(MapActivity.this);
    }

    private void addMarkerMapBox(double latitude, double longitude) {
        mapboxMapView = findViewById(R.id.mapView_mapbox);
//        mapboxMapView.setAccessToken(getString(R.string.mapbox_access_token));
//        mapboxMapView.setTileSource(new MapboxTileLayer("mapbox.satellite"));
        mapboxMapView.setAccessToken(getString(R.string.digtalglobe_access_token_sk));
        mapboxMapView.setTileSource(new MapboxTileLayer(DG_SATELLITE));

        mapboxMapView.setMapRotationEnabled(false);
        LatLng ny = new LatLng(latitude, longitude);
        Marker currentMarker = new Marker("Your location", "", new com.mapbox.mapboxsdk.geometry.LatLng(latitude, longitude));
        currentMarker.setIcon(new Icon(ContextCompat.getDrawable(this, R.drawable.ic_currentloc)));
        mapboxMapView.addMarker(currentMarker);
        mapboxMapView.setCenter(new com.mapbox.mapboxsdk.geometry.LatLng(latitude, longitude));
        mapboxMapView.setZoom(19);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,19));
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
    }
}
