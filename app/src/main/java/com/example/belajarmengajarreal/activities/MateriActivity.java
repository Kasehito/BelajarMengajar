package com.example.belajarmengajarreal.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.example.belajarmengajarreal.R;
import com.example.belajarmengajarreal.models.Materi;
import com.example.belajarmengajarreal.utils.FirebaseClient;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.List;

public class MateriActivity extends AppCompatActivity {

    private WebView webView;
    private Materi materi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materi);

        String materi_id = getIntent().getStringExtra("materi_id");
        FirebaseClient.data().collection("materi").get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot doc : task.getResult()) {
                            if (doc.getId() == materi_id) {
                                this.materi = Materi.create(
                                        doc.getId(),
                                        doc.getString("judul"),
                                        doc.getString("deskripsi"),
                                        null,
                                        doc.getString("video")
                                );
                                setupWebView(this.materi);
                            }
                        }
                    } else {
                        if (task.getException() != null) {
                            task.getException().printStackTrace();
                        }
                        Snackbar.make(
                                requireViewById(R.id.container),
                                "Failed to get materi.",
                                Snackbar.LENGTH_LONG
                        ).show();
                    }
                });

        webView = findViewById(R.id.webView);

        Button mulaiQuizButton = findViewById(R.id.button_mulaiBelajar);
        mulaiQuizButton.setOnClickListener(v -> {
            Intent intent = new Intent(MateriActivity.this, QuizActivity.class);
            startActivity(intent);
        });

    }

    private void setupWebView(Materi materi) {
        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient());
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webView.loadData(materi.getVideoEmbed(), "text/html", "utf-8");
    }
}