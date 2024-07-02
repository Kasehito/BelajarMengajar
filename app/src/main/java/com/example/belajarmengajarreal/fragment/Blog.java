package com.example.belajarmengajarreal.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.belajarmengajarreal.R;
import com.example.belajarmengajarreal.activities.BlogAdapter;
import com.example.belajarmengajarreal.activities.BlogItem;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


import java.util.ArrayList;
import java.util.List;

public class Blog extends Fragment {

    private RecyclerView recyclerView;
    private BlogAdapter blogAdapter;
    private FirebaseFirestore db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blog, container, false);

        recyclerView = view.findViewById(R.id.recyclerviewBlog);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Initialize Firestore
        db = FirebaseFirestore.getInstance();

        // Fetch blog items from Firestore
        fetchBlogItems();

        return view;
    }

    private void fetchBlogItems() {
        db.collection("blogItems") // Replace "blogItems" with your actual collection name
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        List<BlogItem> blogItems = new ArrayList<>();
                        for (DocumentSnapshot document : task.getResult()) {
                            String title = document.getString("title");
                            String description = document.getString("description");
                            String imageUrl = document.getString("imageUrl"); // Ensure you have an imageUrl field or adjust accordingly
                            blogItems.add(new BlogItem(title, description, imageUrl)); // Adjust constructor based on your BlogItem class
                        }
                        // Initialize the adapter with the fetched items
                        blogAdapter = new BlogAdapter(getContext(), blogItems);
                        recyclerView.setAdapter(blogAdapter);
                    } else {
                        Log.d("BlogFragment", "Error getting documents: ", task.getException());
                    }
                });
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
