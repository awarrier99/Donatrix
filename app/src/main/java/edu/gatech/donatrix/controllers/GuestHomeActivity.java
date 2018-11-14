package edu.gatech.donatrix.controllers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import edu.gatech.donatrix.R;

/**
 * An Activity for Guests
 */
public class GuestHomeActivity extends AppCompatActivity {

    /**
     * Activity initializer
     *
     * @param savedInstanceState the activity state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_guest);
    }
}
