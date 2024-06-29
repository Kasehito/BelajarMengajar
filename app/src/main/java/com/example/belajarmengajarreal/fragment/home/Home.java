package com.example.belajarmengajarreal.fragment.home;

import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.example.belajarmengajarreal.R;
import com.example.belajarmengajarreal.fragment.GridAdapter;
import com.example.belajarmengajarreal.models.Materi;
import com.example.belajarmengajarreal.utils.FirebaseClient;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class Home extends Fragment {

    List<Materi> materi;
    GridView gridView;

    public Home() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        TextView tvName = view.findViewById(R.id.tvName);
        gridView = requireView().findViewById(R.id.gridView);

        if (FirebaseClient.user() != null) {
            String displayName = FirebaseClient.user().getDisplayName();
            if (displayName != null && !displayName.isEmpty()) {
                tvName.setText("Halo, " + displayName + " 👋");
            } else {
                tvName.setText("Halo, User 👋");
            }
        } else {
            tvName.setText("Halo, User");
        }
      
        // Get list materi
        Task<QuerySnapshot> queries = FirebaseClient.data().collection("materi").get();
        queries.addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (QueryDocumentSnapshot doc : task.getResult()) {
                    materi.add(Materi.create(
                            doc.getId(),
                            doc.getString("title"),
                            doc.getString("description"),
                            doc.getString("content"),
                            doc.getString("image")
                    ));
                }
            } else {
                if (task.getException() != null) {
                    task.getException().printStackTrace();
                }

                Snackbar.make(
                        requireView(),
                        "Failed to get materi.",
                        Snackbar.LENGTH_LONG
                ).show();
            }
        });

        GridView gridView = view.findViewById(R.id.gridView);
        String[] titles = {"Public Speaking", "Teaching Techniques", "Classroom Management", "Student Motivation"};
        int[] images = {R.drawable.frame_1, R.drawable.frame_2, R.drawable.frame_3, R.drawable.jeki_sayang}; // Add your drawable images

        GridAdapter gridAdapter = new GridAdapter(requireContext(), titles, images);
        gridView.setAdapter(gridAdapter);

        return view;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
}

