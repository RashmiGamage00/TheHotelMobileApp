package com.example.events;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HighteaBooking extends AppCompatActivity {




    EditText eventDate,noOfGuests;

    TextView date_event;

    FirebaseDatabase rootNode;
    DatabaseReference reference;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hightea_booking);
        Button btnConfirm = findViewById(R.id.btnEvent_proceed);
        eventDate = findViewById(R.id.dateOfEvent);
        noOfGuests = findViewById(R.id.guestNo);
        EventsHelper event1 = new EventsHelper();


                btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                reference = FirebaseDatabase.getInstance().getReference().child("Events");

                try{
                    if(TextUtils.isEmpty(eventDate.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter date", Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(noOfGuests.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter valid number of guests", Toast.LENGTH_SHORT).show();

                    else{
                        event1.setEventDate(eventDate.getText().toString().trim());
                        event1.setGuestNo(Integer.parseInt(noOfGuests.getText().toString().trim()));

                        reference.child("Event1").setValue(event1);

                        Toast.makeText(getApplicationContext(), "Booking made Successfully", Toast.LENGTH_SHORT).show();
                    }

                } catch(NumberFormatException e){
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();

                }

                startActivity(new Intent(HighteaBooking.this, SuccessPage.class));

            }
        });
    }
}