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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    EditText nEmail, nPassword;
    Button nLoginBtn, nPasswordBtn, nForgot;
    TextView nCreateBtn;
    ProgressBar progressBar;
    FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //connecting variables with page objects
        nEmail = findViewById(R.id.EmailField);
        nPassword = findViewById(R.id.PasswordField);
        progressBar = findViewById(R.id.progressBar2);
        fAuth = FirebaseAuth.getInstance();
        nLoginBtn = findViewById(R.id.Login);
        nPasswordBtn = findViewById(R.id.forgotPassword);
        nCreateBtn = findViewById(R.id.NeedAccount);

        nLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = nEmail.getText().toString().trim();
                String password = nPassword.getText().toString().trim();

                if(TextUtils.isEmpty((email))) {                //if email value is empty give the user an error
                    nEmail.setError("Email is required");
                    return;
                }
                if(TextUtils.isEmpty(password)) {               //if password value is empty give the user an error
                    nPassword.setError("Password is required");
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);

                //authenticate the user

                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                      if(task.isSuccessful()){
                          Toast.makeText(Login.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                          startActivity(new Intent(getApplicationContext(),MainActivity.class));
                      } else {
                          Toast.makeText(Login.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                      }
                    }
                });
            }
        });



        nCreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent (getApplicationContext(), Register.class));
            }
        });


        nPasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent (getApplicationContext(), ForgotPassword.class));
            }
        });

    }

}
