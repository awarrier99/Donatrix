package edu.gatech.donatrix.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import edu.gatech.donatrix.R;
import edu.gatech.donatrix.data.RESTCaller;
import edu.gatech.donatrix.model.User;

public class LoginActivity extends AppCompatActivity {

    private EditText emailField;
    private EditText passwordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        emailField = findViewById(R.id.loginEmailTextField);
        passwordField = findViewById(R.id.loginPasswordTextField);
    }

    public void onLoginPressed(View view) {
        Map<String, Object> body = new HashMap<>();
        body.put("email", "" + emailField.getText());
        body.put("password", "" + passwordField.getText());

        Map<String, Object> response = RESTCaller.post("https://donatrix-api.herokuapp.com/login", body);
        boolean success = User.testLoggedIn(body);
        Map<String, Object> user = (Map<String, Object>) response.get("user");

        if (success) {
            String type = (String) user.get("type");
            if ("USER".equals(type)) {
                Intent intent = new Intent(LoginActivity.this, UserHomeActivity.class);
                startActivity(intent);
            } else if (type.equals("ADMIN")) {
                Intent intent = new Intent(LoginActivity.this, AdminHomeActivity.class);
                startActivity(intent);
            } else if (type.equals("LOCATION_EMPLOYEE")) {
                Intent intent = new Intent(LoginActivity.this, LocationEmployeeHomeActivity.class);
                intent.putExtra("location_id", (int) user.get("loc_id"));
                startActivity(intent);
            } else {
                Intent intent = new Intent(LoginActivity.this, ManagerHomeActivity.class);
                startActivity(intent);
            }

        } else {
            Toast toast = Toast.makeText(this, "Invalid Login", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }
    public void onForgotPasswordPressed(View view) {
        Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
        startActivity(intent);
    }
}
