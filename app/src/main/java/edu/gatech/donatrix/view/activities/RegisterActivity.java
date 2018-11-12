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
import edu.gatech.donatrix.data.RESTCaller;
import edu.gatech.donatrix.model.Location;
import edu.gatech.donatrix.model.UserType;

public class RegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText nameField;
    private EditText emailField;
    private EditText passwordField;
    private EditText confirmPasswordField;

    private String selectedUserType; //updated everytime a new userType is selected
    private String selectedLocation; //updated everytime a new location is selected


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        ArrayAdapter<UserType> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, UserType.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userTypeSpinner.setAdapter(adapter);

        ArrayAdapter<Location> locationAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, LocationDao.getLocations(this).toArray());
        locationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationSpinner.setAdapter(locationAdapter);
    }

    //Returns the String stored in the name input. Throws an Exception if field is empty
    private String getNameInput() throws InvalidInputException {
        throw InvalidInputException
    }

    //Returns the String stored in the email input. Throw an Excepption if field is empty
    private String getEmailInput() throw InvalidInputException {
        throw InvalidInputException
    }

    //Returns the String stores in password if password matches confirm password. Throws an Excpetion
    //if password fails isValidPassword() of InputValidation interface
    private String getPasswordInput() throws MistmatchingPasswordsException, InvalidInputException {

        throw MismatchingPasswordsException;
        throw InvalidInputExcepetion;
    }

    @Override
    //tracks the value of UserType Spinner and uptades te activities selectedUserType
    public void onUserTypeSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    //tracks the value of Location Spinner and uptades te activities selectedLocation
    public void onLocationSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    //Uses the data on the page to build a user and returns it. If any input is invalid, exception is throw
    //If you must use an enum only use that enum for this activity. Enum has no use outside the spinner
    //NOTE: Potential design flaw in error handling. Leave for now (don't delete this comment)
    private User buildUser() throws InvalidInputException {
        String name = getNameInput();
        String email = getEmailInput();
        String password = getPasswordInput();

        switch (selectedUserType) {
            case ("Location Employee") {
                return  LocationEmployee(name, email, password, selectedLocation);
                break; //technically not needed by put here for syntactic elegance
            }
            //continue the switch...
        }
    }

    public void onRegisterPressed(View view) {
        String name = getNameInput();
        String email = getNameInput();
        String Password = getPasswordInput();

        User user = buildUser();

        try {
            registerUser(user);
        } catch (DuplicateUserException e) {
            //handle error. A User is considered equal if and only if their emails are the same.
        }
    }
}