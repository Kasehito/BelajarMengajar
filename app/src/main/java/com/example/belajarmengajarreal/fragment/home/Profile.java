package com.example.belajarmengajarreal.fragment.home;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.belajarmengajarreal.R;
import com.example.belajarmengajarreal.activities.EditProfile;
import com.example.belajarmengajarreal.activities.LoginPage;
import com.example.belajarmengajarreal.utils.FirebaseClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Profile extends Fragment {

    private SharedPreferences.Editor editor;
    private Button btnLogout;
  
    public Profile() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // Find the Edit Profile button
        Button editProfileButton = view.findViewById(R.id.buttonEdit);

        // Set an OnClickListener to navigate to the Edit Profile activity
        editProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EditProfile.class);
                startActivity(intent);
            }
        });

        // Find the TextView for displaying the greeting
        TextView greetingTextViewUp = view.findViewById(R.id.tvNameAtas);
        TextView greetingTextViewDown = view.findViewById(R.id.tvNameBawah);

        // Set the greeting text
        if (FirebaseClient.user() != null) {
            String displayName = FirebaseClient.user().getDisplayName();
            if (displayName != null && !displayName.isEmpty()) {
                greetingTextViewUp.setText("Halo, " + displayName+" 👋");
                greetingTextViewDown.setText(displayName);
            } else {
                greetingTextViewDown.setText("Halo, User 👋");
                greetingTextViewUp.setText("User");
            }
        } else {
            greetingTextViewUp.setText("Halo, User");
            greetingTextViewDown.setText("User");
        }

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Remove username from SharedPreferences
                editor.remove("username");
                editor.apply();

                // Start MainActivity and finish current activity
                Intent intent = new Intent(requireActivity(), LoginPage.class);
                startActivity(intent);
                requireActivity().finish();
            }
        });

        return view;
    }
}