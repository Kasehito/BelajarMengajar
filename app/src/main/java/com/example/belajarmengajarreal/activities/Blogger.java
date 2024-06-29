package com.example.belajarmengajarreal.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.belajarmengajarreal.R;

public class Blogger extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blogger);

        TextView titleTextView = findViewById(R.id.blog_title);
        TextView descriptionTextView = findViewById(R.id.blog_description);
        ImageView imageView = findViewById(R.id.blogImage);

        String title = getIntent().getStringExtra("title");
        String description = getIntent().getStringExtra("description");
        int imageResourceId = getIntent().getIntExtra("imageResourceId", -1);

        titleTextView.setText(title);
        descriptionTextView.setText(description);
        Glide.with(this).load(imageResourceId).into(imageView);


        ImageButton btnBack = findViewById(R.id.button_Back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
