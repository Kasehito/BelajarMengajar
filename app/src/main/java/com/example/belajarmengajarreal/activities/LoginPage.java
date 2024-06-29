package com.example.belajarmengajarreal.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.belajarmengajarreal.R;
import com.example.belajarmengajarreal.utils.FirebaseClient;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPage extends AppCompatActivity {

    private static final String PREFS_NAME = "MyPrefsFile";
    private static final String PREFS_IS_LOGGED_IN = "isLoggedIn";
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();

        if (mAuth.getCurrentUser() != null) {
            goToMainActivity();
        }

        final EditText usernameEditText = findViewById(R.id.etUsername);
        final EditText passwordEditText = findViewById(R.id.etPassword);
        Button signUpButton = findViewById(R.id.button_signUp_Page);
        Button loginButton = findViewById(R.id.button_login);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSignUpActivity();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Dummy check for login credentials
                String email = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                loginUser(email, password);
            }
        });
    }

    private void loginUser(String email, String password) {
        FirebaseClient.login(email, password)
                .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                goToMainActivity();
                            } else {
                                Toast.makeText(
                                        LoginPage.this,
                                        "Aldi salah masukin user nih!",
                                        Toast.LENGTH_SHORT
                                ).show();
                            }
                        });

//        mAuth.signInWithEmailAndPassword(email, password)
//                .addOnCompleteListener(this, task -> {
//                    if (task.isSuccessful()) {
//                        // Sign in success
//                        FirebaseUser user = mAuth.getCurrentUser();
//                        SharedPreferences settings = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
//                        SharedPreferences.Editor editor = settings.edit();
//                        editor.putBoolean(PREFS_IS_LOGGED_IN, true);
//                        editor.apply();
//
//                        goToMainActivity();
//                    } else {
//                        // If sign in fails, display a message to the user.
//                        Toast.makeText(LoginPage.this, "Authentication Failed.", Toast.LENGTH_SHORT).show();
//                    }
//                });
    }
//                  :3
////                Nanti ada password sama username disini ya nevun (nev: fak u)
//                if (username.equals("user") && password.equals("password")) {
//                    SharedPreferences settings = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
//                    SharedPreferences.Editor editor = settings.edit();
//                    editor.putBoolean(PREFS_IS_LOGGED_IN, true);
//                    editor.apply();
//
//                    goToMainActivity();
//                } else {
//                    // Show login error
//                }
    private void goToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    private void goToSignUpActivity() {
        Intent intent = new Intent(this, Signup.class);
        startActivity(intent);
        finish();
    }
}