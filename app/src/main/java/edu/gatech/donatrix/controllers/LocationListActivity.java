package edu.gatech.donatrix.controllers;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import edu.gatech.donatrix.R;
import edu.gatech.donatrix.dao.LocationDao;
import edu.gatech.donatrix.model.Location;

public class LocationListActivity extends AppCompatActivity
        implements AdapterView.OnItemSelectedListener {

    @Nullable
    private Location location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_list);

        Spinner locationSpinner = findViewById(R.id.locationListLocationSpinner);
        locationSpinner.setOnItemSelectedListener(this);

        ArrayAdapter<Location> locationArrayAdapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, LocationDao.
                getLocations(this).toArray());
        locationArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationSpinner.setAdapter(locationArrayAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        location = (Location) parent.getItemAtPosition(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        location = null;
    }


    public void onBackButtonPressed(View view) {
        finish();
    }

    public void onChooseButtonPressed(View view) {
        Log.d("Zeke", location.getName());
        Intent intent = new Intent(LocationListActivity.this, ItemListActivity.class);
        intent.putExtra("location_id", location.getKey());
        startActivity(intent);
    }
}
