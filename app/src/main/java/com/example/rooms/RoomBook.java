package com.example.rooms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RoomBook extends AppCompatActivity {


    EditText check_in, check_out, adultNo, childNo;




    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_book);
        Button btnConfirm = findViewById(R.id.btn_confirmBooking);
        check_in = findViewById(R.id.date_checkin);
        check_out = findViewById(R.id.date_checkout);
        adultNo = findViewById(R.id.adultNo);
        childNo = findViewById(R.id.childNo);

        Room room1 = new Room();
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reference = FirebaseDatabase.getInstance().getReference().child("Rooms");

                try{
                    if(TextUtils.isEmpty(check_in.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter check-in date", Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(check_out.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter check-out date", Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(adultNo.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter valid number of adults", Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(childNo.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter valid number of children", Toast.LENGTH_SHORT).show();
                    else{
                        room1.setCheck_in(check_in.getText().toString().trim());
                        room1.setCheck_out(check_out.getText().toString().trim());
                        room1.setAdultNo(Integer.parseInt(adultNo.getText().toString().trim()));
                        room1.setChildNo(Integer.parseInt(childNo.getText().toString().trim()));



                        reference.child("Room1").setValue(room1);

                        Toast.makeText(getApplicationContext(), "Booking made successfully", Toast.LENGTH_SHORT).show();
                    }

                } catch(NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                }


                    startActivity(new Intent(RoomBook.this, SuccessPage.class));
                }
        });
    }
}