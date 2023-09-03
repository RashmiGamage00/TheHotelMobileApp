package com.example.events;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

public class MyBookings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bookings);

        Button update = findViewById(R.id.btnUpdate);
        Button delete = findViewById(R.id.btnCancel);
        TextView date = findViewById(R.id.displayDate);
        TextView guestNo = findViewById(R.id.displayGuestNo);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("Events");
                delRef.addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.hasChild("Event")){
                            DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("Events").child("Event");
                            dbRef.removeValue();
                            Toast.makeText(getApplicationContext(), "Booking cancelled successfully", Toast.LENGTH_SHORT).show();
                        }
                        else
                            Toast.makeText(getApplicationContext(), "Cancellation Failed", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                startActivity(new Intent(MyBookings.this, HighteaBooking.class));
            }
        });

        DatabaseReference mdb;
        mdb = FirebaseDatabase.getInstance().getReference();
        mdb.child("Events").child("Event1").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful()){
                    String x = String.valueOf(task.getResult().getValue());
                    Log.d("ERR", "onComplete: ");
                    Log.d("ERR",x);
                    JSONObject jObject = null;

                    try {
                        jObject = new JSONObject(x);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    String eventDate = null;
                    String guestNo = null;
                    try {
                        eventDate = jObject.getString("eventDate");
                        guestNo = jObject.getString(guestNo);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Log.d("ERR", String.valueOf(eventDate));
                    Log.d("ERR", String.valueOf(guestNo));

                }else{
                    Log.d("ERR","False");

                }
            }
        });

    }
}