package com.example.educationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PasswordUpdatedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_updated);
    }

    public void MovetoLogin(View view) {
        Intent intent = new Intent(PasswordUpdatedActivity.this , LoginActivity.class);
        startActivity(intent);
    }

    public void moveBack(View view) {
    }
}