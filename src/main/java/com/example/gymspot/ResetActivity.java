package com.example.gymapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class ResetActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);

        email = (TextView)findViewById(R.id.reset_email);
        btn =  (Button)findViewById(R.id.reset_button);
        nAuth = FirebaseAuth. getInstance();

        
