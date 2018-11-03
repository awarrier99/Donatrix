package edu.gatech.donatrix3.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import edu.gatech.donatrix3.R;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    public void onLoginPressed(View view) {
        Intent loginActivityIntent = new Intent(WelcomeActivity.this, LoginActivity.class);
        startActivity(loginActivityIntent);
    }
}
