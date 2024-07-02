package com.example.belajarmengajarreal.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.belajarmengajarreal.R;
import com.example.belajarmengajarreal.models.QuizModel;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    private List<QuizModel> quizList = new ArrayList<>();
    private int currentQuestionIndex = 0;
    private TextView TxtSoal;
    private RadioGroup radioGroup;
    private RadioButton PilihanA, PilihanB, PilihanC;
    private ImageButton btnNext, btnPrev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        ImageButton btnBack = findViewById(R.id.buttonBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Initialize UI components
        TxtSoal = findViewById(R.id.Txt_soal);
        radioGroup = findViewById(R.id.radioGroup);
        PilihanA = findViewById(R.id.PilihanA);
        PilihanB = findViewById(R.id.PilihanB);
        PilihanC = findViewById(R.id.PilihanC);
        btnNext = findViewById(R.id.button_next);
        btnPrev = findViewById(R.id.button_prev);

        // Load quiz questions
        loadQuizQuestions();

        // Display the first question
        displayQuestion(currentQuestionIndex);

        // Handle option selection
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            // Logic to handle option selection
        });

        // Navigate to the next question
        btnNext.setOnClickListener(v -> {
            if (currentQuestionIndex < quizList.size() - 1) {
                currentQuestionIndex++;
                displayQuestion(currentQuestionIndex);
            }
        });

        // Navigate to the previous question
        btnPrev.setOnClickListener(v -> {
            if (currentQuestionIndex > 0) {
                currentQuestionIndex--;
                displayQuestion(currentQuestionIndex);
            }
        });
    }

    private void loadQuizQuestions() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("quizQuestions").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (QueryDocumentSnapshot document : task.getResult()) {
                    String question = document.getString("question");
                    String optionA = document.getString("optionA");
                    String optionB = document.getString("optionB");
                    String optionC = document.getString("optionC");
                    int correctAnswer = document.getLong("correctAnswer").intValue();
                    quizList.add(new QuizModel(question, optionA, optionB, optionC, correctAnswer));
                }
                if (!quizList.isEmpty()) {
                    displayQuestion(currentQuestionIndex);
                }
            } else {
                Log.w("QuizActivity", "Error getting documents.", task.getException());
            }
        });
    }

    private void displayQuestion(int index) {
        QuizModel currentQuestion = quizList.get(index);
        TxtSoal.setText(currentQuestion.getSoal());
        PilihanA.setText(currentQuestion.getJawaban1());
        PilihanB.setText(currentQuestion.getJawaban2());
        PilihanC.setText(currentQuestion.getJawaban3());
        radioGroup.clearCheck();
    }
}