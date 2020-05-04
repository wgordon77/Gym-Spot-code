package com.example.gymspot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.content.Intent;

import com.google.android.gms.tasks.OnFailureListener;
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

public class editprofile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofile);


        final EditText xEditName, xEditEmail, xEditAge, xEditGender, xEditCity, xEditStyle, xEditFitness, xEditMember;
        Button xeBack, xSubmit;
        FirebaseAuth fAuth;
     final    FirebaseFirestore fStore;
      final  FirebaseUser user;

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
         user = fAuth.getCurrentUser();


        xeBack = findViewById(R.id.eBack);
        xSubmit = findViewById(R.id.submitButttn);
        Intent data = getIntent();
        String fullName = data.getStringExtra("fullname");
        String email = data.getStringExtra("email");
        String age = data.getStringExtra("age");
        String gender = data.getStringExtra("gender");
        String city = data.getStringExtra("city");
        String style = data.getStringExtra("styleOfWorkOut");
        String member = data.getStringExtra("gymMembership");
        String fitness = data.getStringExtra("fitnessLevel");


        xEditName = findViewById(R.id.editFullName);
        xEditEmail = findViewById(R.id.editEmail);
        xEditAge = findViewById(R.id.editAge);
        xEditGender = findViewById(R.id.editGender);
        xEditCity = findViewById(R.id.editCity);
        xEditStyle = findViewById(R.id.editStyleOfWorkout);
        xEditFitness = findViewById(R.id.editFitnessLevel);
        xEditMember = findViewById(R.id.editGymmembership);


        xEditName.setText(fullName);
        xEditEmail.setText(email);
        xEditAge.setText(age);
        xEditGender.setText(gender);
        xEditCity.setText(city);
        xEditStyle.setText(style);
        xEditMember.setText(member);
        xEditFitness.setText(fitness);


        xeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), viewprofile.class));
            }
        });


        xSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (xEditName.getText().toString().isEmpty() || xEditEmail.getText().toString().isEmpty() || xEditGender.getText().toString().isEmpty() || xEditAge.getText().toString().isEmpty() || xEditCity.getText().toString().isEmpty() || xEditFitness.getText().toString().isEmpty() || xEditStyle.getText().toString().isEmpty() || xEditMember.getText().toString().isEmpty()) {

                    Toast.makeText(editprofile.this, "One or many fields are empty", Toast.LENGTH_SHORT).show();
                    return;

                }

                final String email = xEditEmail.getText().toString();
                user.updateEmail(email).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        DocumentReference docRef = fStore.collection("users").document(user.getUid());
                        Map<String, Object> edited = new HashMap<>();
                        edited.put("email", email);
                        edited.put("fName", xEditName.getText().toString());
                        edited.put("city", xEditCity.getText().toString());
                        edited.put("age", xEditAge.getText().toString());
                        edited.put("gender", xEditGender.getText().toString());
                        edited.put("fitnessLevel", xEditFitness.getText().toString());
                        edited.put("gymMembership", xEditMember.getText().toString());
                        edited.put("styleOfWorkOut", xEditStyle.getText().toString());
                        docRef.update(edited).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(editprofile.this, "Email succesfully updated", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), viewprofile
                                        .class));
                                finish();
                            }
                        });
                        Toast.makeText(editprofile.this, "Email succesfully updated", Toast.LENGTH_SHORT).show();
                    }
                }) .addOnFailureListener(new OnFailureListener() {

                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(editprofile.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }
}
