package edu.gatech.donatrix3.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import edu.gatech.donatrix3.R;
import edu.gatech.donatrix3.dao.UserDao;
import edu.gatech.donatrix3.model.User;
import edu.gatech.donatrix3.model.UserType;

public class RegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText nameField;
    private EditText emailField;
    private EditText passwordField;
    private EditText confirmPasswordField;
    private Spinner userTypeSpinner;

    private User user;

    private UserType userType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nameField = (EditText) findViewById(R.id.registerNameInputText);
        emailField = (EditText) findViewById(R.id.registerEmailInputText);
        passwordField = (EditText) findViewById(R.id.registerPasswordInputText);
        confirmPasswordField = (EditText) findViewById(R.id.registerConfirmPasswordInputText);
        userTypeSpinner = (Spinner) findViewById(R.id.registerUserTypeSpinner);


        ArrayAdapter<UserType> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, UserType.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userTypeSpinner.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        userType = (UserType) parent.getItemAtPosition(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        userType = UserType.USER;
    }

    public void onAddPressed(View view) {
        if (passwordField.equals(confirmPasswordField)) {
            if (UserDao.checkRegisteredUser(emailField.toString(), passwordField.toString(), this)) {
                if (userType == UserType.USER) {
                    User user = new User(emailField.toString(), passwordField.toString(), nameField.toString(), false, UserType.USER);
                    UserDao.registerUser(user, this);
                }
            }
        }
    }

    public void onCancelPressed(View view) {
        finish();
    }

}
