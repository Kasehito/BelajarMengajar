package com.example.belajarmengajarreal.fragment.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.belajarmengajarreal.R;
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
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        gridView = requireView().findViewById(R.id.gridView);

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
}