package com.tounsiasmaa.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button bSignup;
    TextView tvSign;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bSignup = (Button) findViewById(R.id.bSignup);
        tvSign = (TextView) findViewById(R.id.tvSign);

        bSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.bSignup:
                        startActivity(
                                new Intent(MainActivity.this, Register.class));
                        break;
                }
                
            }
        });
        tvSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.tvSign:
                        startActivity(
                                new Intent(MainActivity.this, SignIn.class));
                        break;
                }

            }
        });


    }
}