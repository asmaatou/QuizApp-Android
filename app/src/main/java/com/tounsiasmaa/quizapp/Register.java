package com.tounsiasmaa.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class Register extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText etFirstName,etLastName,etMail,etPawd;
    Button bRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etFirstName = (EditText) findViewById(R.id.etFName);
        etLastName = (EditText) findViewById(R.id.etLName);
        etMail = (EditText) findViewById(R.id.etEmail);
        etPawd = (EditText) findViewById(R.id.etPwd);
        bRegister = (Button) findViewById(R.id.bReg);

        mAuth = FirebaseAuth.getInstance();

        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.bReg:
                        registerUser();
                        break;

                }

            }
        });
    }

    private void registerUser() {

        String email = etMail.getText().toString().trim();
        String firstname = etFirstName.getText().toString().trim();
        String lastname = etLastName.getText().toString().trim();
        String pwd = etPawd.getText().toString().trim();

        if (firstname.isEmpty()){
            etFirstName.setError("First Name required ! ");
            etFirstName.requestFocus();
            return;
        }
        if (lastname.isEmpty()){
            etLastName.setError("Last Name required ! ");
            etLastName.requestFocus();
            return;
        }
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
            etPawd.setError("Password required ! ");
            etPawd.requestFocus();
            return;
        }
        if (pwd.length() < 6){
            etPawd.setError("The minimum password length is 6");
            etPawd.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email,pwd)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            User user = new User(firstname,lastname,email);
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(Register.this, "User has been registered successfully !",Toast.LENGTH_LONG).show();
                                    }else{
                                        Toast.makeText(Register.this, "Failed to register !",Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }else {
                            Toast.makeText(Register.this, "Failed to register !",Toast.LENGTH_LONG).show();
                        }
                    }
                });


    }
}
