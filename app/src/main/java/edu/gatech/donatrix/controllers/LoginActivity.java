package edu.gatech.donatrix.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import edu.gatech.donatrix.R;
import edu.gatech.donatrix.data.RESTCaller;
import edu.gatech.donatrix.model.User;

public class LoginActivity extends AppCompatActivity {

    private EditText emailField;
    private EditText passwordField;
    private CallbackManager callbackManager;
    private ProgressBar mBar;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        emailField = findViewById(R.id.loginEmailTextField);
        passwordField = findViewById(R.id.loginPasswordTextField);

        callbackManager = CallbackManager.Factory.create();

        LoginButton loginButton = (LoginButton) findViewById(R.id.facebook_login_button);
        loginButton.setReadPermissions(Arrays.asList("email"));
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                mBar = new ProgressBar(LoginActivity.this, null, android.R.attr.progressBarStyleLarge);
                mBar.setVisibility(View.VISIBLE);

                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), (object, response) -> {
                    mBar.setVisibility(View.INVISIBLE);
                    Log.d("response", response.toString());
                    getData(object);
                });

                Bundle parameters = new Bundle();
                parameters.putString("fields", "email");
                request.setParameters(parameters);
                request.executeAsync();
                Intent facebookLoginIntent = new Intent(LoginActivity.this, UserHomeActivity.class);
                startActivity(facebookLoginIntent);
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

        //if already login
        //if (AccessToken.getCurrentAccessToken() != null) {
        //    txtEmail.setText(AccessToken.getCurrentAccessToken().getUserId());
        //}
    }
    private void getData(JSONObject object) {
        try {
            emailField.setText(object.getString("email"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
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
}
