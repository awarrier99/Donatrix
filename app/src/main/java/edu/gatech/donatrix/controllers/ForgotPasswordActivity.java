package edu.gatech.donatrix.controllers;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import edu.gatech.donatrix.R;

public class ForgotPasswordActivity extends AppCompatActivity {
    private EditText emailField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        emailField = findViewById(R.id.forgotPasswordTextInput);
    }
    public void onEmailButtonPressed(View view) {
        sendEmail();
    }
    protected void sendEmail() {
        Log.i("Send email", "");

        String[] TO = new String[1];
        TO[0] = emailField.getText().toString();
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");

        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Reset Your Password");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Hi, this is a basic test for email verification");

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.i("Finished sending email", "");
        } catch (android.content.ActivityNotFoundException e) {
            Toast.makeText(ForgotPasswordActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }
}
