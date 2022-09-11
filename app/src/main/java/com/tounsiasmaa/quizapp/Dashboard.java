package com.tounsiasmaa.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class Dashboard extends AppCompatActivity {
    ImageView ivMap,ivQuiz,ivLog,ivCamera,ivLoc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ivMap=(ImageView) findViewById(R.id.ivMap);
        ivQuiz=(ImageView) findViewById(R.id.ivQuiz);
        ivLog=(ImageView) findViewById(R.id.ivLog);
        ivLoc=(ImageView) findViewById(R.id.ivLoc);
        ivCamera=(ImageView) findViewById(R.id.ivCamera);
        ivLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.ivLoc:
                        startActivity(
                                new Intent(Dashboard.this, Location.class));
                        break;
                }
            }
        });
        ivCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.ivCamera:
                        startActivity(
                                new Intent(Dashboard.this, Camera.class));
                        break;
                }
            }
        });
        ivLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.ivLog:
                        Toast.makeText(getApplicationContext(), "Merci de votre Participation !", Toast.LENGTH_SHORT).show();
                        startActivity(
                                new Intent(Dashboard.this,SignIn.class));
                        finish();
                        break;
                }


            }
        });
        ivQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.ivQuiz:
                        startActivity(
                                new Intent(Dashboard.this, Quiz1.class));
                        break;
                }
            }
        });
        ivMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.ivMap:
                        startActivity(
                                new Intent(Dashboard.this, Maps.class));
                        break;
                }
            }
        });
    }
}