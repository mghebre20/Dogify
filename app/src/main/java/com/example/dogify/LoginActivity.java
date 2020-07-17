package com.example.dogify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity {

    public static final String TAG = "LoginActivity";

    //member variables
    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //if user is signed in already, then goToMainActivity
        if(ParseUser.getCurrentUser() != null) {
            goToMainActivity();
        }

        //assigning member variables to elements
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        //set a onClickListener on the login button
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick login button ");
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                loginUser(username, password);

            }
        });
    }

        private void loginUser(String username, String password) {
            Log.i(TAG, "Attempting to log in user" + username);
            //loginBackground executes logic on background thread
            ParseUser.logInInBackground(username, password, new LogInCallback() {
                @Override
                public void done(ParseUser user, ParseException e) {
                    //if request fails, the ParseException wil not be null
                    if(e != null){
                        Log.e(TAG, "Error with login", e);
                        Toast.makeText(LoginActivity.this, "Issue with login", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    //if request succeded, the ParseException will be null
                    goToMainActivity();
                    //toast to indicate to the user that they have successfully logged in
                    Toast.makeText(LoginActivity.this,"Success!", Toast.LENGTH_SHORT).show();
                }
            });
        }

    private void goToMainActivity() {
        //Intent to traverse from here to MainActivity
        //Takes in two parameters: this and our navigation point, MainActivity
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);

        //finish the LoginActivity once navigated to MainActivity
        //this will remove loginActivity from the back stack
        finish();
    }
}