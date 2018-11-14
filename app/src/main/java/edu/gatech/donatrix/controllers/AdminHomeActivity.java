package edu.gatech.donatrix.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import edu.gatech.donatrix.R;

/**
 * This is the Admin Home
 */
public class AdminHomeActivity extends AppCompatActivity {

    /**
     * Activity initializer
     *
     * @param savedInstanceState saved state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_admin);
    }
}
