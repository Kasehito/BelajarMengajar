package com.example.belajarmengajarreal.fragment.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.belajarmengajarreal.R;
import com.example.belajarmengajarreal.activities.BlogAdapter;
import com.example.belajarmengajarreal.activities.BlogItem;

import java.util.ArrayList;
import java.util.List;

public class Blog extends Fragment {
  
    private RecyclerView recyclerView;
    private BlogAdapter blogAdapter;

    public Blog() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blog, container, false);

        recyclerView = view.findViewById(R.id.recyclerviewBlog);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        List<BlogItem> blogItems = new ArrayList<>();
        blogItems.add(new BlogItem("Apa sih kunci menjadi guru yang baik?", "Klik untuk Selengkapnya", R.drawable.frame_1));
        blogItems.add(new BlogItem("Tips mengajar efektif", "Klik untuk Selengkapnya", R.drawable.frame_2));
        blogItems.add(new BlogItem("Cara memotivasi siswa", "Klik untuk Selengkapnya", R.drawable.frame_3));

        blogAdapter = new BlogAdapter(getContext(), blogItems);
        recyclerView.setAdapter(blogAdapter);

        return view;
    }
}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
