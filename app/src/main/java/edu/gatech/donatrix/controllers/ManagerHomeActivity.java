package edu.gatech.donatrix.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import edu.gatech.donatrix.R;

public class ManagerHomeActivity extends AppCompatActivity {

    /**
     * Activity initializer
     *
     * @param savedInstanceState the activity state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_manager);
    }

    /**
     * Called when map button is pressed
     *
     * @param view the current view
     */
    public void onMapButtonPressed(View view) {
        Intent intent  = new Intent(ManagerHomeActivity.this, MapsActivity.class);
        startActivity(intent);
    }
}
