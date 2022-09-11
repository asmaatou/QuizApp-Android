package com.tounsiasmaa.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Maps extends AppCompatActivity {
    EditText etSource,etDestination;
    Button bTrack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        etSource = findViewById(R.id.etSource);
        etDestination = findViewById(R.id.etDestination);
        bTrack = findViewById(R.id.bTrack);

        bTrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String vSource = etSource.getText().toString().trim();
                String vDestination = etDestination.getText().toString().trim();

                if(vSource.equals("") && vDestination.equals("")){
                    Toast.makeText(getApplicationContext(),"Enter Both Locations",Toast.LENGTH_SHORT ).show();
                }else {
                    DisplayTrack(vSource,vDestination);
                }
            }
        });
    }

    private void DisplayTrack(String vSource, String vDestination) {
        try {
            Uri uri = Uri.parse("https://www.google.co.in/maps/dir/"+ vSource + "/" + vDestination);

            Intent intent = new Intent(Intent.ACTION_VIEW,uri);
            intent.setPackage("com.google.android.apps.maps");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }catch (ActivityNotFoundException e){
            Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps");

            Intent intent = new Intent(Intent.ACTION_VIEW,uri);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }
}