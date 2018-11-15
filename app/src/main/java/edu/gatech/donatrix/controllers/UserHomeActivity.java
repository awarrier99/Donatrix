package edu.gatech.donatrix.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import edu.gatech.donatrix.R;

/**
 * The home base for Users
 */
public class UserHomeActivity extends AppCompatActivity {

    /**
     * Activity initializer
     *
     * @param savedInstanceState the activity state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_user);
    }

    /**
     * Called when the location list detects a press
     *
     * @param view the current view
     */
    public void onLocationListPressed(View view) {
        Intent intent = new Intent(this, LocationListActivity.class);
        startActivity(intent);
    }
}
