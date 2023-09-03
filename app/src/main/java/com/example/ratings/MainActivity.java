package com.example.ratings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button submit = findViewById(R.id.submit);
       TextView feedback = findViewById(R.id.feedback);
        Feedback feedback1 = new Feedback();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reference = FirebaseDatabase.getInstance().getReference().child("Feedbacks");

                try{
                    if(TextUtils.isEmpty(feedback.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Field cannot be left blank", Toast.LENGTH_SHORT).show();

                    else{
                        feedback1.setFeedback(feedback.getText().toString().trim());

                        reference.child("Feedback1").setValue(feedback1);

                        Toast.makeText(getApplicationContext(), "Feedback submitted", Toast.LENGTH_SHORT).show();
                    }

                } catch(NumberFormatException e){
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();

                }

                startActivity(new Intent(MainActivity.this, SuccessPage.class));

            }

        });
    }
}