package edu.gatech.donatrix.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import edu.gatech.donatrix.R;
import edu.gatech.donatrix.dao.LocationDao;
import edu.gatech.donatrix.dao.UserDao;
import edu.gatech.donatrix.data.RESTCaller;
import edu.gatech.donatrix.model.Admin;
import edu.gatech.donatrix.model.Location;
import edu.gatech.donatrix.model.LocationEmployee;
import edu.gatech.donatrix.model.Manager;
import edu.gatech.donatrix.model.User;
import edu.gatech.donatrix.model.UserType;

public class RegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText nameField;
    private EditText emailField;
    private EditText passwordField;
    private EditText confirmPasswordField;

    private UserType userType;
    private Location location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        nameField = (EditText) findViewById(R.id.registerNameInputText);
        emailField = (EditText) findViewById(R.id.registerEmailInputText);
        passwordField = (EditText) findViewById(R.id.registerPasswordInputText);
        confirmPasswordField = (EditText) findViewById(R.id.registerConfirmPasswordInputText);
        Spinner userTypeSpinner = (Spinner) findViewById(R.id.registerUserTypeSpinner);
        Spinner locationSpinner = (Spinner) findViewById(R.id.registerLocationSpinner);
        userTypeSpinner.setOnItemSelectedListener(this);
        locationSpinner.setOnItemSelectedListener(this);

        ArrayAdapter<UserType> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, UserType.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userTypeSpinner.setAdapter(adapter);

        ArrayAdapter<Location> locationAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, LocationDao.getLocations(this).toArray());
        locationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationSpinner.setAdapter(locationAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Spinner spinner = (Spinner) parent;
        if (spinner.getId() == R.id.registerUserTypeSpinner) {
            userType = (UserType) spinner.getItemAtPosition(position);
        } else if (spinner.getId() == R.id.registerLocationSpinner){
            location = (Location) parent.getItemAtPosition(position);
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        userType = UserType.USER;
        location = null;
    }

    private boolean isEmailValid(String email) {
        Pattern validEmail = Pattern.compile("^[A-Za-z0-9]+@[A-Za-z0-9]+\\.[A-Za-z]+$");
        return validEmail.matcher(email).matches();
    }

    public void onRegisterPressed(View view) {
        if (("" + passwordField.getText()).equals(("" + confirmPasswordField.getText()))) {
            Map<String, Object> body = new HashMap<>();
            body.put("email", "" + emailField.getText());

            Map<String, Object> response = RESTCaller.post("https://donatrix-api.herokuapp.com/checkUser", body);
            boolean success = (boolean) response.get("success");

            if (!success) {
                body.put("password", "" + passwordField.getText());
                body.put("name", "" + nameField.getText());
                body.put("locked", 0);
                body.put("type", userType.getType());

                Class clazz = LoginActivity.class;

                if (userType.getType().equals("LOCATION_EMPLOYEE")) {
                    body.put("loc_id", location.getKey());

                } else if (userType.getType().equals("MANAGER")) {
                    clazz = ManagerHomeActivity.class;
                }

                response = RESTCaller.post("https://donatrix-api.herokuapp.com/register", body);
                success = (boolean) response.get("success");

                if (success) {
                    Intent intent = new Intent(RegisterActivity.this, clazz);
                    startActivity(intent);
                } else {
                    Toast toast = Toast.makeText(this, "Something is wrong", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0,0);
                    toast.show();
                }
            } else {
                Toast toast = Toast.makeText(this, "Something is wrong", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0,0);
                toast.show();
            }
        } else {
            Toast toast = Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }
}
