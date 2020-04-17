package com.example.gymspot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    //variables
    EditText mFullname, mEmail, mPassword, mGender, mAge, mCity, mFitnessLevel, mStyleOfWorkout, mGymMembership;
    Button mCreateAccountBtn;
    FirebaseAuth fAuth;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        mFullname = findViewById(R.id.FullName);
        mEmail = findViewById(R.id.Email);
        mPassword = findViewById(R.id.Password);
        mGender = findViewById(R.id.Gender);
        mAge = findViewById(R.id.Age);
        mCity = findViewById(R.id.City);
        mFitnessLevel = findViewById(R.id.FitnessLevel);
        mStyleOfWorkout = findViewById(R.id.StyleOfWorkout);
        mGymMembership = findViewById(R.id.GymMembership);
        mCreateAccountBtn = findViewById(R.id.CreateAccount);
        fAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);


        if(fAuth.getCurrentUser() != null) {                //if user already has an account in the data, take them to the login page
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }
        mCreateAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullName = mFullname.getText().toString().trim();
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                String gender = mGender.getText().toString().trim();
                String age = mAge.getText().toString().trim();
                String city = mCity.getText().toString().trim();
                String fitnessLevel = mFitnessLevel.getText().toString().trim();
                String styleOfWorkout = mStyleOfWorkout.getText().toString().trim();
                String gymMembership = mGymMembership.getText().toString().trim();

                if(TextUtils.isEmpty((email))) {                //if email value is empty give the user an error
                    mEmail.setError("Email is required");
                    return;
                }
                    if(TextUtils.isEmpty(password)) {               //if password value is empty give the user an error
                        mPassword.setError("Password is required");
                        return;
                    }
                if(TextUtils.isEmpty(gender)) {                     //if gender value is empty, give the user an error
                    mGender.setError("Gender is required");
                    return;
                }
                if(TextUtils.isEmpty(city)) {                   //if city value is empty, give user an error
                    mCity.setError("City is required");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                //register user in firebase
                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(Register.this, "User Created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        } else{
                            Toast.makeText(Register.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
            });
            }
        };

