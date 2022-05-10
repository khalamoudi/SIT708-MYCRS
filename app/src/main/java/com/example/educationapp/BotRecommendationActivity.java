package com.example.educationapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.educationapp.Adapters.RecommendedCoursesAdapter;

public class BotRecommendationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bot_recommendation);



        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.assistent_rcommended_courses_recyclerview);
        // set a LinearLayoutManager with default vertical orientation
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);


        RecommendedCoursesAdapter recommendedCoursesAdapter = new RecommendedCoursesAdapter(this,4);
        recyclerView.setAdapter(recommendedCoursesAdapter); // set the Adapter to RecyclerView

    }
}