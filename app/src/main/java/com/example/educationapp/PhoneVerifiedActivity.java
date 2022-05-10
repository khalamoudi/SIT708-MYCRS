package com.example.educationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PhoneVerifiedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_verified);
    }

    public void MovetoLogins(View view) {
        Intent intent = new Intent(PhoneVerifiedActivity.this , LoginActivity.class);
        startActivity(intent);
    }
}