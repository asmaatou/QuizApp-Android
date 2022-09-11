package com.tounsiasmaa.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Reset extends AppCompatActivity {

    EditText etEmail;
    Button bReset;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);

        etEmail = (EditText) findViewById(R.id.etEmail);
        bReset = (Button) findViewById(R.id.bReset);

        auth = FirebaseAuth.getInstance();

        bReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ResetPawd();

            }
        });
    }

    private void ResetPawd() {
        String email = etEmail.getText().toString().trim();
        if (email.isEmpty()) {

            etEmail.setError("Email required !");
            etEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("Please provide a valid email !");
            etEmail.requestFocus();
            return;
        }
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(Reset.this,"Check your email to reset your password !  ", Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(Reset.this,"Try Again", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}

 