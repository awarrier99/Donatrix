package edu.gatech.donatrix.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.Toast;

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
            if (!(UserDao.checkRegisteredUser("" + emailField.getText(), "" + passwordField.getText(), this))) {
                if (userType.getType().equals("USER")) {
                    User user = new User("" + emailField.getText(), "" + passwordField.getText(), "" + nameField.getText(), false, UserType.USER);
                    UserDao.registerUser(user, this);
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                } else if (userType.getType().equals("ADMIN")) {
                    User user = new Admin("" + emailField.getText(), "" + passwordField.getText(), "" + nameField.getText());
                    UserDao.registerUser(user, this);
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                } else if (userType.getType().equals("LOCATION_EMPLOYEE")) {
                    User user = new LocationEmployee("" + emailField.getText(), "" + passwordField.getText(), "" + nameField.getText(), location);
                    UserDao.registerUser(user, this);
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        } else {
            Toast toast = Toast.makeText(this, "Something is wrong", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }

    public void onCancelPressed(View view) {
        finish();
    }

}
