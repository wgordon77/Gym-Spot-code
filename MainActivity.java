package com.example.faq;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView faq = (TextView) findViewById(id.faq);
        
            @Override


                <TextView
        android:id="@+id/Question 1"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="How does Gymspot work?"
        android:textColor="@color/black"></TextView>

 <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:id="@+id/Question 2"
        android:text="How do I look for local sporting events near me?"
        android:textColor="@color/black"/>

<TextView
        android:id="@+id/Question 3"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="I am having trouble uploading videos"
        android:textColor="@color/black"/>

    <TextView
        android:id="@+id/Question 4"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="How can I filter my search options?"/>
    }
}
