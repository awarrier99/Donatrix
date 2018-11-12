package edu.gatech.donatrix.controllers;

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
        try {
            //login
            //navigate to Donatrix screen
        } catch InvalidCredentialsException {
            //handle
        } catch LockedAccountException {
            //handle
        }
        catch NoNetworkAccessException {
            //handle
        }
    }

    public onRegisterPressed(View view) {
        //navigate to the register screen
    }

    public onCancelPressed(View view) {
        //navigate back to welcome screen
    }

}
