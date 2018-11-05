package edu.gatech.donatrix.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import edu.gatech.donatrix.R;
import edu.gatech.donatrix.dao.UserDao;
import edu.gatech.donatrix.model.User;
import edu.gatech.donatrix.model.UserType;

public class LoginActivity extends AppCompatActivity {

    private EditText emailField;
    private EditText passwordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailField = (EditText) findViewById(R.id.loginEmailTextField);
        passwordField = (EditText) findViewById(R.id.loginPasswordTextField);
    }

    public void onCancelPressed(View view) {
        finish();
    }

    public void onLoginPressed(View view) {
        if (UserDao.checkRegisteredUser("" + emailField.getText(), "" + passwordField.getText(), this)) {
            if (UserDao.getUser("" + emailField.getText(), this).getUserType().getType().equals("USER")) {
                Intent intent = new Intent(LoginActivity.this, UserHomeActivity.class);
                startActivity(intent);
            } else if (UserDao.getUser("" + emailField.getText(), this).getUserType().getType().equals("ADMIN")) {
                Intent intent = new Intent(LoginActivity.this, AdminHomeActivity.class);
                startActivity(intent);
            } else if (UserDao.getUser("" + emailField.getText(), this).getUserType().getType().equals("LOCATION_EMPLOYEE")) {
                Intent intent = new Intent(LoginActivity.this, LocationEmployeeHomeActivity.class);
                intent.putExtra("employee_user", "" + emailField.getText());
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
