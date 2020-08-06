package com.example.dogify;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.dogify.Models.User;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

import com.spotify.sdk.android.authentication.AuthenticationClient;
import com.spotify.sdk.android.authentication.AuthenticationRequest;
import com.spotify.sdk.android.authentication.AuthenticationResponse;

public class LoginActivity extends AppCompatActivity {

    public static final String TAG = "LoginActivity";

    private static final String CLIENT_ID = "27325e06a80b40bbadb9cd01ac815115";
    private static final String REDIRECT_URI = "com.example.dogify://callback";
    private static final int REQUEST_CODE = 15;
    private static final String SCOPES = "user-read-recently-played,user-library-modify,user-read-email,user-read-private";

    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;
    private Button btnSpotify;

    private static RequestQueue queue;
    private static SharedPreferences sharedPref;
    public static final String SHARED_PREFS = "SPOTIFY";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //if user is signed in already, then goToMainActivity
        if (ParseUser.getCurrentUser() != null) {
            goToMainActivity();
        }

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnSpotify = findViewById(R.id.btnSpotify);
        sharedPref = this.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        queue = Volley.newRequestQueue(this);

        btnLogin.setOnClickListener(view ->  {
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                loginUser(username, password);
                Log.i(TAG, "onClick login button ");
        });

        btnSpotify.setOnClickListener(view -> {
            authenticateSpotify();
            Log.i(TAG, "onClick spotify ");
        });
    }

    private void loginUser(String username, String password) {
        Log.i(TAG, "Attempting to log in user" + username);
        //loginBackground executes logic on background thread
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                //if request fails, the ParseException wil not be null
                if (e != null) {
                    Log.e(TAG, "Error with login", e);
                    Toast.makeText(LoginActivity.this, "Issue with login", Toast.LENGTH_SHORT).show();
                    return;
                }
                //if request succeeded, the ParseException will be null
                goToMainActivity();
                Toast.makeText(LoginActivity.this, "Success!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void goToMainActivity() {
        //traverse from here to MainActivity
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    private void authenticateSpotify() {
        AuthenticationRequest.Builder builder = new AuthenticationRequest.Builder(CLIENT_ID, AuthenticationResponse.Type.TOKEN, REDIRECT_URI);
        builder.setScopes(new String[]{SCOPES});
        AuthenticationRequest request = builder.build();
        AuthenticationClient.openLoginActivity(this, REQUEST_CODE, request);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if (requestCode == REQUEST_CODE) {
            AuthenticationResponse response = AuthenticationClient.getResponse(resultCode, intent);

            switch (response.getType()) {
                case TOKEN:
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("token", response.getAccessToken());
                    editor.commit();
                    Log.d(TAG, "Got Auth Token Successfully!");
                    waitForUserInfo();
                    break;
                case ERROR:
                default:
                    Log.e(TAG, "Issue with response");
                    break;
            }
        }
    }

    public void waitForUserInfo() {
        SpotifyClient client = new SpotifyClient();
        client.getCurrentUser(() -> {
            User user = new User();
            user.getUser();
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("userid", user.getId());
            editor.commit();
            Log.d(TAG, "Got user id!");
            goToMainActivity();
        });
    }

//    private void traverseToMainActivity() {
//        Intent intentSpotify = new Intent(this, MainActivity.class);
//        startActivity(intentSpotify);
//        finish();
//    }
}
