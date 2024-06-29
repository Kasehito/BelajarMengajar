package com.example.belajarmengajarreal.utils;

import androidx.annotation.NonNull;
import androidx.arch.core.executor.TaskExecutor;

import com.example.belajarmengajarreal.models.Materi;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class FirebaseClient {
    public static FirebaseAuth auth() {
        return FirebaseAuth.getInstance();
    }
    public static FirebaseUser user() { return auth().getCurrentUser(); }

    public static Task<AuthResult> login(String email, String password) {
        return auth().signInWithEmailAndPassword(email, password);
    }

    public static Task<AuthResult> register(String email, String password) {
        return auth().createUserWithEmailAndPassword(email, password);
    }

    public static void logout() {
        auth().signOut();
    }

    public static FirebaseFirestore data() { return FirebaseFirestore.getInstance(); }

}
