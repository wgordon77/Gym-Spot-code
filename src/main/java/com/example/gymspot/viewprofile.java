package com.example.gymspot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

public class viewprofile extends AppCompatActivity {
    TextView mFullname, mEmail, mGender, mAge, mCity, mFitnessLevel, mStyleOfWorkout, mGymMembership;
    Button mBack, mMakeChanges;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    ProgressBar progressBar;
    String userID;
    FirebaseUser user;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewprofile);

        mFullname = findViewById(R.id.profileFullName);
        mEmail = findViewById(R.id.profileEmail);
        mGender = findViewById(R.id.profileGender);
        mAge = findViewById(R.id.profileAge);
        mCity = findViewById(R.id.profileCity);
        mFitnessLevel = findViewById(R.id.profileFitnessLevel);
        mStyleOfWorkout = findViewById(R.id.profileStyleOfWorkout);
        mGymMembership = findViewById(R.id.profileGymmembership);
        mBack = findViewById(R.id.backBttn);
        mMakeChanges = findViewById(R.id.makeChanges);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        userID = fAuth.getCurrentUser().getUid();

        DocumentReference documentReference = fStore.collection("user").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                mFullname.setText(documentSnapshot.getString("fName"));
                mEmail.setText(documentSnapshot.getString("email"));
                mGender.setText(documentSnapshot.getString("gender"));
                mAge.setText(documentSnapshot.getString("age"));
                mCity.setText(documentSnapshot.getString("city"));
                mFitnessLevel.setText(documentSnapshot.getString("fitnessLevel"));
                mStyleOfWorkout.setText(documentSnapshot.getString("styleOfWorkOut"));
                mGymMembership.setText(documentSnapshot.getString("gymMembership"));
            }
        });

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), settings
                        .class));
            }
        });

        mMakeChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i = new Intent(v.getContext(),editprofile.class);

                i.putExtra("fullname",mFullname.getText().toString());
                i.putExtra("email",mEmail.getText().toString());
                i.putExtra("gender",mGender.getText().toString());
                i.putExtra("age",mAge.getText().toString());
                i.putExtra("city",mCity.getText().toString());
                i.putExtra("fitnessLevel",mFitnessLevel.getText().toString());
                i.putExtra("styleOfWorkOut",mStyleOfWorkout.getText().toString());
                i.putExtra("gymMembership",mGymMembership.getText().toString());

                startActivity(i);

        }
    });
}
}
