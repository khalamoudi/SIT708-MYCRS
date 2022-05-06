package com.example.educationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ResetCodeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_code);
    }

    public void VerfiyCode(View view) {

        Intent intent = new Intent(ResetCodeActivity.this , NewPasswordActivity.class);
        startActivity(intent);
    }
}