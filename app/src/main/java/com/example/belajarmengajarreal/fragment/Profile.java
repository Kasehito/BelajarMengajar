package com.example.belajarmengajarreal.fragment;

import android.content.Context;
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
import com.example.belajarmengajarreal.activities.LoginActivity;
import com.example.belajarmengajarreal.utils.FirebaseClient;

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
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        Button editProfileButton = view.findViewById(R.id.buttonEdit);
        btnLogout = view.findViewById(R.id.buttonLogout);

        editProfileButton.setOnClickListener(v -> {
            // Ensure this Intent correctly references the EditProfile activity
            Intent intent = new Intent(getActivity(), EditProfile.class);
            startActivity(intent);
        });

        // Initialization of SharedPreferences.Editor
        SharedPreferences prefs = getActivity().getSharedPreferences("YourPrefName", Context.MODE_PRIVATE);
        editor = prefs.edit();

        TextView greetingTextViewUp = view.findViewById(R.id.tvNameAtas);
        TextView greetingTextViewDown = view.findViewById(R.id.tvNameBawah);

        if (FirebaseClient.user() != null) {
            String displayName = FirebaseClient.user().getDisplayName();
            if (displayName != null && !displayName.isEmpty()) {
                greetingTextViewUp.setText("Halo, " + displayName + " 👋");
                greetingTextViewDown.setText(displayName);
            } else {
                greetingTextViewDown.setText("Halo, User 👋");
                greetingTextViewUp.setText("User");
            }
        } else {
            greetingTextViewUp.setText("Halo, User");
            greetingTextViewDown.setText("User");
        }

        btnLogout.setOnClickListener(v -> {
            FirebaseClient.logout();
            Intent logoutIntent = new Intent(requireActivity(), LoginActivity.class);
            startActivity(logoutIntent);
            requireActivity().finish();
        });

        return view;
    }
}