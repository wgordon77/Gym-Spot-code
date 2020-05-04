package com.example.gymspot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ForgotPasswordActivity extends AppCompatActivity {

    EditText userEmail;
    Button userPass;
    
    FirebaseAuth firebaseAuth;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        userEmail = findViewById(R.id.etuserEmail);
        userPass = findViewById(R.id.btForgotPass);
       
        firebaseAuth =FirebaseAuth.getInstance();
        
        userPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                firebaseAuth.sendPasswordResetEmail(userEmail.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<Void>(){
                  @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful(){
                            Toast.makeText(ForgotPasswordActivity.this, "Password sent to your Email", Toast.LENGTH_LONG.show();
                        }else{
                            Toast.makeText(ForgotPasswordActivity.this, task.getExcception.getMessage(), Toast.LENGTH_LONG.show();
                          }
                    }
                });
            }
        });
    }
}

        
