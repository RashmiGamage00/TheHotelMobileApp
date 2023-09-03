package com.example.mypayment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddCardDetails extends AppCompatActivity {

    EditText txttype, txtname, txtnumber, txtdate, txtcvc;
    Button btnSave, btnshow, btnupdate, btnDelete;
    DatabaseReference dbRef;

    private void clearControls() {
        txttype.setText("");
        txtname.setText("");
        txtnumber.setText("");
        txtdate.setText("");
        txtcvc.setText("");
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card_details);

        txttype = findViewById(R.id.cardtype);
        txtname = findViewById(R.id.cardholdername);
        txtnumber = findViewById(R.id.cardnumber);
        txtdate = findViewById(R.id.editTextDate);
        txtcvc = findViewById(R.id.cvc);

        btnSave = findViewById(R.id.Save);
        btnshow = findViewById(R.id.show);
        btnupdate=findViewById(R.id.update);
        btnDelete=findViewById(R.id.delete);

        addcard std = new addcard();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("addcard");
                try {
                    if (TextUtils.isEmpty(txttype.getText().toString()))
                        Toast.makeText(getApplicationContext(), "enter card type", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtname.getText().toString()))
                        Toast.makeText(getApplicationContext(), "enter name", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtnumber.getText().toString()))
                        Toast.makeText(getApplicationContext(), "enter number", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtdate.getText().toString()))
                        Toast.makeText(getApplicationContext(), "enter date", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtcvc.getText().toString()))
                        Toast.makeText(getApplicationContext(), "enter cvc", Toast.LENGTH_SHORT).show();

                    else {
                        //Take input from the user and assign to instance
                        std.setType(txttype.getText().toString().trim());
                        std.setName(txtname.getText().toString().trim());
                        std.setNumber(txtnumber.getText().toString().trim());
                        std.setDate(txtdate.getText().toString().trim());
                        std.setCvc(txtcvc.getText().toString().trim());
                        //insert into database
                        dbRef.child("card").setValue(std);
                        //feedback to the user via toast..
                        Toast.makeText(getApplicationContext(), "Data Saved Successfully", Toast.LENGTH_SHORT).show();
                        clearControls();

                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Invalid", Toast.LENGTH_SHORT).show();
                }
            }

        });
        btnshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("addcard").child("card");
                readRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChildren()) {
                            txttype.setText(dataSnapshot.child("type").getValue().toString());
                            txtname.setText(dataSnapshot.child("name").getValue().toString());
                            txtnumber.setText(dataSnapshot.child("number").getValue().toString());
                            txtdate.setText(dataSnapshot.child("date").getValue().toString());
                            txtcvc.setText(dataSnapshot.child("cvc").getValue().toString());
                        } else
                            Toast.makeText(getApplicationContext(), "No Source to display", Toast.LENGTH_SHORT).show();

                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference updRef=FirebaseDatabase.getInstance().getReference().child("addcard");
                updRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild("card")){
                            try{
                                std.setType(txttype.getText().toString().trim());
                                std.setName(txtname.getText().toString().trim());
                                std.setNumber(txtnumber.getText().toString().trim());
                                std.setDate(txtdate.getText().toString().trim());
                                std.setCvc(txtcvc.getText().toString().trim());

                                dbRef=FirebaseDatabase.getInstance().getReference().child("addcard").child("card");
                                dbRef.setValue(std);
                                clearControls();
                                //Feedback to the toast user via a toast..
                                Toast.makeText(getApplicationContext(), "Data Update Sucessfully", Toast.LENGTH_SHORT).show();
                            }
                            catch (NumberFormatException e){
                               Toast.makeText(getApplicationContext(), "Invalid number", Toast.LENGTH_SHORT).show();
                            }


                        }
                        else
                            Toast.makeText(getApplicationContext(), "No Source to display", Toast.LENGTH_SHORT).show();
                }
                   @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                   }
                });
           }


       });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference delRef=FirebaseDatabase.getInstance().getReference().child("addcard");
                delRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild("card")){
                            dbRef=FirebaseDatabase.getInstance().getReference().child("addcard").child("card");
                            dbRef.removeValue();
                            clearControls();
                            Toast.makeText(getApplicationContext(), "Data Deleted Successfully", Toast.LENGTH_SHORT).show();

                        }
                        else
                            Toast.makeText(getApplicationContext(), "No Source to delete", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });


    }
}


