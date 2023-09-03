package com.example.mypayment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Dashboardui extends AppCompatActivity implements View.OnClickListener {

    public CardView paymcard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboardui);

        paymcard = (CardView) findViewById(R.id.paymentcard);
        paymcard.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.paymentcard:
                intent = new Intent(this, AddCardDetails.class);
                startActivity(intent);

        }
    }
}