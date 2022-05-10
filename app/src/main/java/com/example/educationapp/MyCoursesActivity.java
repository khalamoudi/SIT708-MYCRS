package com.example.educationapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.educationapp.Adapters.MyCoursesAdapter;

public class MyCoursesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_my_courses);


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.my_courses_recyclerview_list);
        // set a LinearLayoutManager with default vertical orientation
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);


        MyCoursesAdapter myCoursesAdapter = new MyCoursesAdapter(this,4);
        recyclerView.setAdapter(myCoursesAdapter); // set the Adapter to RecyclerView


    }

    public void paidCourseDetail(View view) {

        Intent intent = new Intent(MyCoursesActivity.this, CourseDetailPaidActivity.class);
        startActivity(intent);
    }

    public void addcourse(View view) {

        Intent intent = new Intent(MyCoursesActivity.this, CourseDetailActivity.class);
        startActivity(intent);
    }
}