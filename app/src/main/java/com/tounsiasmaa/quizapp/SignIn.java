package com.tounsiasmaa.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignIn extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText etMail, etPassword;
    Button bSignIn;
    TextView tvForgetPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        etMail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPwd);
        bSignIn = (Button) findViewById(R.id.bSign);
        tvForgetPwd = (TextView) findViewById(R.id.tvFPwd);

        mAuth = FirebaseAuth.getInstance();
        tvForgetPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.tvFPwd:
                        startActivity(
                                new Intent(SignIn.this, Reset.class));
                        break;
                }
            }
        });
        bSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.bSign:
                        userLogin();
                        break;
                }
            }
        });


    }


    private void userLogin() {
        String email = etMail.getText().toString().trim();
        String pwd = etPassword.getText().toString().trim();

        if (email.isEmpty()){
            etMail.setError("Email required ! ");
            etMail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            etMail.setError("Email invalid !");
            etMail.requestFocus();
            return;
        }
        if (pwd.isEmpty()){
            etPassword.setError("Password required ! ");
            etPassword.requestFocus();
            return;
        }
        if (pwd.length() < 6){
            etPassword.setError("The minimum password length is 6");
            etPassword.requestFocus();
            return;
        }
        mAuth.signInWithEmailAndPassword(email, pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    //
                    startActivity(new Intent(SignIn.this, Dashboard.class ));
                }else{
                    Toast.makeText(SignIn.this, "Failed to LOGIN !",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
