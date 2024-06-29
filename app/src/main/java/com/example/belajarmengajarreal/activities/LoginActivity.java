package com.example.belajarmengajarreal.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.belajarmengajarreal.R;
import com.example.belajarmengajarreal.utils.FirebaseClient;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;

public class LoginActivity extends AppCompatActivity {

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

                // hide keyboard
                View focusedView = getCurrentFocus();
                if (focusedView != null) {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }

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

                        if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                            Snackbar.make(
                                    findViewById(android.R.id.content),
                                    "Email atau password salah!",
                                    Snackbar.LENGTH_LONG
                            ).show();
                            return;
                        }

                        if (task.getException() instanceof FirebaseNetworkException) {
                            Snackbar.make(
                                    findViewById(android.R.id.content),
                                    "Tidak ada koneksi internet!",
                                    Snackbar.LENGTH_LONG
                            ).show();
                            return;
                        }

                        task.getException().printStackTrace();
                        Snackbar.make(
                                findViewById(android.R.id.content),
                                "Terjadi kesalahan!",
                                Snackbar.LENGTH_LONG
                        ).show();
                    }
                });
    }
    private void goToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    private void goToSignUpActivity() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
        finish();
    }
}