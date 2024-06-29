package com.example.belajarmengajarreal.fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.example.belajarmengajarreal.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Home extends Fragment {

    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;

    public Home() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        TextView tvName = view.findViewById(R.id.tvName);

        if (currentUser != null) {
            String displayName = currentUser.getDisplayName();
            if (displayName != null && !displayName.isEmpty()) {
                tvName.setText("Halo, " + displayName + " 👋");
            } else {
                tvName.setText("Halo, User 👋");
            }
        } else {
            tvName.setText("Halo, User");
        }

        GridView gridView = view.findViewById(R.id.gridView);
        String[] titles = {"Public Speaking", "Teaching Techniques", "Classroom Management", "Student Motivation"};
        int[] images = {R.drawable.frame_1, R.drawable.frame_2, R.drawable.frame_3, R.drawable.jeki_sayang}; // Add your drawable images

        com.example.belajarmengajarreal.fragment.GridAdapter gridAdapter = new com.example.belajarmengajarreal.fragment.GridAdapter(getContext(), titles, images);
        gridView.setAdapter(gridAdapter);

        return view;
    }
}
