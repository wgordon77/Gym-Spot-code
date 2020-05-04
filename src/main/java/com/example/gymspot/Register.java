package com.example.gymspot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    public static final String TAG = "TAG";
    //variables
    EditText mFullname, mEmail, mPassword, mGender, mAge, mCity, mFitnessLevel, mStyleOfWorkout, mGymMembership;
    Button mCreateAccountBtn;
    TextView nLoginBtn;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    ProgressBar progressBar;
    String userID;
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
        fStore = FirebaseFirestore.getInstance();
        progressBar = findViewById(R.id.progressBar);

        nLoginBtn = findViewById(R.id.AlreadyRegisted);

        if(fAuth.getCurrentUser() != null) {                //if user already has an account in the data, take them to the login page
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }
        mCreateAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String fullName = mFullname.getText().toString().trim();
               final String email = mEmail.getText().toString().trim();
                final String password = mPassword.getText().toString().trim();
                final String gender = mGender.getText().toString().trim();
                final String age = mAge.getText().toString().trim();
                final String city = mCity.getText().toString().trim();
                final String fitnessLevel = mFitnessLevel.getText().toString().trim();
                final String styleOfWorkout = mStyleOfWorkout.getText().toString().trim();
                final String gymMembership = mGymMembership.getText().toString().trim();

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
                //also store their information
                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(Register.this, "User Created", Toast.LENGTH_SHORT).show();
                            userID = fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fStore.collection( "user").document(userID);
                            Map<String, Object> user = new HashMap<>();
                            user.put("fName",fullName);
                            user.put("email", email);
                            user.put("gender", gender);
                            user.put("age", age);
                            user.put("city", city);
                            user.put("fitnessLevel", fitnessLevel);
                            user.put("styleOfWorkOut", styleOfWorkout);
                            user.put("gymMembership", gymMembership);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                  Log.d(TAG, "onSuccess: User profile is created for" + userID);

                                }
                            });
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        } else{
                            Toast.makeText(Register.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
            });


        nLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent (getApplicationContext(), Login.class));
            }
        });


            }
        };

