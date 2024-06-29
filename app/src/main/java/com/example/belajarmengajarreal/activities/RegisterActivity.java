package com.example.belajarmengajarreal.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.belajarmengajarreal.R;
import com.example.belajarmengajarreal.utils.FirebaseClient;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {


    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();

        final EditText emailEditText = findViewById(R.id.etUsername);
        final EditText passwordEditText = findViewById(R.id.etPassword);
        Button signUpButton = findViewById(R.id.button_signUp);
        Button login = findViewById(R.id.button_login_page);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             goToLoginActivity();
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                // hide keyboard
                View focusedView = getCurrentFocus();
                if (focusedView != null) {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }

                createAccount(email, password);
            }
        });
    }

    private void createAccount(String email, String password) {
       FirebaseClient.register(email, password)
               .addOnCompleteListener(task -> {
                   if (task.isSuccessful()) {
                       goToMainActivity();
                   } else {

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
    private void goToLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}