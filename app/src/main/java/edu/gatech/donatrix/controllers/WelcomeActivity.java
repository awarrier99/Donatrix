package edu.gatech.donatrix.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import edu.gatech.donatrix.R;

/**
 * The main page for the Application.
 */
public class WelcomeActivity extends AppCompatActivity {

    /**
     * Activity initializer
     *
     * @param savedInstanceState the activity state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    /**
     * Called when login button is pressed
     *
     * @param view the current view
     */
    public void onLoginPressed(View view) {
        Intent loginActivityIntent = new Intent(WelcomeActivity.this, LoginActivity.class);
        startActivity(loginActivityIntent);
    }

    /**
     * Called when the register button is pressed
     *
     * @param view the current view
     */
    public void onRegisterPressed(View view) {
        Intent intent = new Intent(WelcomeActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    /**
     * when the guest login button is pressed
     *
     * @param view the current view
     */
    public void onGuestLoginPressed(View view) {
        Intent intent = new Intent(WelcomeActivity.this, GuestHomeActivity.class);
        startActivity(intent);
    }
}
