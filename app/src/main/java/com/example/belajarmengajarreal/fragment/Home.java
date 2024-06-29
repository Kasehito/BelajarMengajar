package com.example.belajarmengajarreal.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.belajarmengajarreal.R;
import com.example.belajarmengajarreal.activities.GridAdapter;
import com.example.belajarmengajarreal.models.Materi;
import com.example.belajarmengajarreal.utils.FirebaseClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Home extends Fragment {

    private List<Materi> materiList;
    private GridView gridView;

    public Home() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        materiList = new ArrayList<>(); // Initialize materiList
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        TextView tvName = view.findViewById(R.id.tvName);
        gridView = view.findViewById(R.id.gridView);

        // Set greeting message based on user's display name
        if (FirebaseClient.user() != null) {
            String displayName = FirebaseClient.user().getDisplayName();
            tvName.setText("Halo, " + (displayName != null && !displayName.isEmpty() ? displayName : "User") + " 👋");
        } else {
            tvName.setText("Halo, User");
        }

        // Load materi data from Firestore
        FirebaseClient.data().collection("materi").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            materiList.clear(); // Clear existing materiList
                            for (QueryDocumentSnapshot doc : task.getResult()) {
                                materiList.add(Materi.create(
                                        doc.getId(),
                                        doc.getString("judul"),
                                        doc.getString("deskripsi"),
                                        null,
                                        doc.getString("video")
                                ));
                            }
                            // Update GridView with materi data
                            GridAdapter gridAdapter = new GridAdapter(requireContext(), materiList);
                            gridView.setAdapter(gridAdapter);
                        } else {
                            if (task.getException() != null) {
                                task.getException().printStackTrace();
                            }
                            Snackbar.make(view, "Failed to get materi.", Snackbar.LENGTH_LONG).show();
                        }
                    }
                });

        return view;
    }
}
