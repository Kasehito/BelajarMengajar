package com.example.belajarmengajarreal.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.belajarmengajarreal.fragment.Blog;
import com.example.belajarmengajarreal.fragment.Home;
import com.example.belajarmengajarreal.fragment.Profile;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.example.belajarmengajarreal.R;


public class HomeActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView = findViewById(R.id.bottomNav);
        bottomNavigationView.setOnItemSelectedListener(navListener);

        // Load the default fragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame, new Home())
                .commit();
      
}
  
    private BottomNavigationView.OnItemSelectedListener navListener =
            new BottomNavigationView.OnItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    if (item.getItemId() == R.id.Home) {
                        selectedFragment = new Home();
                    } else if (item.getItemId() == R.id.Blog) {
                        selectedFragment = new Blog();
                    } else if (item.getItemId() == R.id.Profile) {
                        selectedFragment = new Profile();
                    }

                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frame, selectedFragment)
                            .commit();

                    return true;
                }
            };
    }
