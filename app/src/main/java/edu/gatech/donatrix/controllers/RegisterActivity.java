package edu.gatech.donatrix.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.Spinner;

import java.util.regex.Pattern;

import edu.gatech.donatrix.R;
import edu.gatech.donatrix.dao.UserDao;
import edu.gatech.donatrix.model.User;
import edu.gatech.donatrix.model.UserType;
import edu.gatech.donatrix.dao.LocationDao;
import edu.gatech.donatrix.model.Admin;
import edu.gatech.donatrix.model.Location;
import edu.gatech.donatrix.model.LocationEmployee;
import edu.gatech.donatrix.model.LocationType;

public class RegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText nameField;
    private EditText emailField;
    private EditText passwordField;
    private EditText confirmPasswordField;
    private Spinner userTypeSpinner;
    private Spinner locationSpinner;

    private UserType userType;
    private Location location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nameField = (EditText) findViewById(R.id.registerNameInputText);
        emailField = (EditText) findViewById(R.id.registerEmailInputText);
        passwordField = (EditText) findViewById(R.id.registerPasswordInputText);
        confirmPasswordField = (EditText) findViewById(R.id.registerConfirmPasswordInputText);
        userTypeSpinner = (Spinner) findViewById(R.id.registerUserTypeSpinner);
        locationSpinner = (Spinner) findViewById(R.id.registerLocationSpinner);


        ArrayAdapter<UserType> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, UserType.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userTypeSpinner.setAdapter(adapter);

        Log.d("Zeke", Integer.toString(LocationDao.getLocations(this).size()));
        ArrayAdapter<Location> locationAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, LocationDao.getLocations(this).toArray(new Location[LocationDao.getLocations(this).size()]));
        locationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationSpinner.setAdapter(locationAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        userType = (UserType) parent.getItemAtPosition(position);
        location = (Location) parent.getItemAtPosition(position);

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
        if (passwordField.equals(confirmPasswordField)) {
            if (UserDao.checkRegisteredUser(emailField.toString(), passwordField.toString(), this)) {
                if (userType.equals(UserType.USER)) {
                    User user = new User(emailField.toString(), passwordField.toString(), nameField.toString(), false, UserType.USER);
                    UserDao.registerUser(user, this);
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                } else if (userType.equals(UserType.ADMIN)) {
                    User user = new Admin(emailField.toString(), passwordField.toString(), nameField.toString());
                    UserDao.registerUser(user, this);
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                } else if (userType.equals(UserType.LOCATION_EMPLOYEE)) {
                    User user = new LocationEmployee(emailField.toString(), passwordField.toString(), nameField.toString(), location);
                    UserDao.registerUser(user, this);
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        }
    }

    public void onCancelPressed(View view) {
        finish();
    }

}
