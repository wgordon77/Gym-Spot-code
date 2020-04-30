package com.example.gym;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class ResetActivity extends AppCompatActivity {
    private TextView email;
    private Button btn;
    private firebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);

        email = (TextView)findViewById(R.id.reset_email);
        btn =  (Button)findViewById(R.id.reset_button);
        nAuth = FirebaseAuth. getInstance();

        btn.setOnClickListener(new View.OnClickListener(){
            @Override
                     public void onClick(View V)  {
                String useremail = email.getText().toString().trim()

                        if(TextUtils.isEmpty(useremail)){
                            Toast.makeText(ResetActivity.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                        }else{
                            mAuth.sendPasswordResetEmail(useremail).addOnCompleteListener(new OnCompleteListener<Void>(){
                                @Override
                                public void onComplete(@NonNull Task <Void> task){
                                    if(task.isSucessful()){
                                        Toast.makeText(ResetActivity.this, "Check your Email \n and reset +" +
                                                "your password", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                    }else{
                                        String message = task.getException().getMessage();
                                        Toast.makeText(ResetActivity.this, "Error Occurred:" + message, Toast.LENGTH_SHORT).show();
                                    }
                                }
                            })
                        }

            }
        }
        )
    }
}
