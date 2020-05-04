package com.example.gymspot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ResetActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);

        email = (TextView)findViewById(R.id.reset_email);
        btn =  (Button)findViewById(R.id.reset_button);
        nAuth = FirebaseAuth. getInstance();

        
