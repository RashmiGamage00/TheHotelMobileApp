package com.example.rooms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MyBookings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bookings);

        Button update = findViewById(R.id.btnUpdate);
        Button delete = findViewById(R.id.btnCancel);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("Rooms");
                delRef.addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.hasChild("Room1")){
                            DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("Rooms").child("Room1");
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
                startActivity(new Intent(MyBookings.this, RoomBook.class));
            }
        });


    }
}