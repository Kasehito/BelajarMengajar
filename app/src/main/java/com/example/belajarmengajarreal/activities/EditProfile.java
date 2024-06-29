package com.example.belajarmengajarreal.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.belajarmengajarreal.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import android.widget.ImageButton;

public class EditProfile extends AppCompatActivity {

    private EditText editTextName, editTextPassword;
    private Button buttonSave;
    private ImageButton backButton; // Change to ImageButton
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        editTextName = findViewById(R.id.etName);
        editTextPassword = findViewById(R.id.etPassword);
        buttonSave = findViewById(R.id.buttonSave);
        backButton = findViewById(R.id.buttonBack);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveProfile();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Load current user data
        loadUserData();
    }

    private void loadUserData() {
        if (currentUser != null) {
            editTextName.setText(currentUser.getDisplayName());
            // Note: For security reasons, we can't retrieve the current password
            editTextPassword.setHint("Enter new password");
        }
    }

    private void saveProfile() {
        final String newName = editTextName.getText().toString().trim();
        final String newPassword = editTextPassword.getText().toString().trim();

        if (newName.isEmpty()) {
            Toast.makeText(this, "Please enter a name", Toast.LENGTH_SHORT).show();
            return;
        }

        // Update display name
        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(newName)
                .build();

        currentUser.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(EditProfile.this, "Profile name updated", Toast.LENGTH_SHORT).show();

                            // If a new password was entered, update it
                            if (!newPassword.isEmpty()) {
                                updatePassword(newPassword);
                            } else {
                                finish(); // Close the activity if no password change
                            }
                        } else {
                            Toast.makeText(EditProfile.this, "Failed to update profile name", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void updatePassword(String newPassword) {
        currentUser.updatePassword(newPassword)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(EditProfile.this, "Password updated", Toast.LENGTH_SHORT).show();
                            finish(); // Close the activity after updating
                        } else {
                            Toast.makeText(EditProfile.this, "Failed to update password", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}