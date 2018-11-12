package edu.gatech.donatrix.view.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class WelcomeActivty extends AppCompatActivity {

    //TODO Javadocs
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    public void onLoginPressed(View view) {
        Intent loginActivityIntent = new Intent(WelcomeActivity.this, LoginActivity.class);
        startActivity(loginActivityIntent);
    }

    public void onRegisterPressed(View view) {
        Intent intent = new Intent(WelcomeActivity.this, RegisterActivity.class);
        startActivity(intent);
    }
}
