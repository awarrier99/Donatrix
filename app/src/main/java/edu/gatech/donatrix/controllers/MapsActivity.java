package edu.gatech.donatrix.controllers;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import edu.gatech.donatrix.R;
import edu.gatech.donatrix.dao.Database;
import edu.gatech.donatrix.model.Location;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        googleMap.getUiSettings().setZoomControlsEnabled(true);

        final Database dataService = Database.getInstance(this);

        List<Location> dataList = dataService.getLocations();

        for (Location de: dataList) {
            LatLng loc = new LatLng(Double.parseDouble(de.getLatitude()), Double.parseDouble(de.getLongitude()));
            googleMap.addMarker(new MarkerOptions().position(loc).title(de.getName()).snippet(de.getNumber()));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(loc));
        }

//        mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter());

//        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

//    class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {
//
//        private final View mContentsView;
//
//        CustomInfoWindowAdapter() {
//            mContentsView = getLayoutInflater().inflate(R.layout.custom_map_pin_layout, null);
//        }
//
//
//    }
}
