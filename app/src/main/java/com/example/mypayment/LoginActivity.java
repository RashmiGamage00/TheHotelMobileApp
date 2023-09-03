package com.example.mypayment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textViewSignup;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textViewSignup=(TextView) findViewById(R.id.textViewsSignup);
        textViewSignup.setOnClickListener(this);

        login=(Button)findViewById(R.id.Login);
        login.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.textViewsSignup:
                startActivity(new Intent(this,RegisterActivity.class));
                break;


            case R.id.Login:
                startActivity(new Intent(this,Dashboardui.class));
                break;
        }
    }

    }
