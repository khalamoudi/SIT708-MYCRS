package com.example.educationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    }

    public void MovetoSignin(View view) {
        Intent intent = new Intent(SignupActivity.this , LoginActivity.class);
        startActivity(intent);
    }

    public void AccountCreate(View view) {
        Intent intent = new Intent(SignupActivity.this , VerificationActivity.class);
        startActivity(intent);
    }
}