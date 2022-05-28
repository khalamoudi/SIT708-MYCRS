package com.example.educationapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.educationapp.Adapters.DeletedCoursesAdapter;
import com.example.educationapp.Adapters.MyCoursesAdapter;
import com.example.educationapp.Models.PredictResponse;
import com.example.educationapp.Models.RecommendationItem;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MyCoursesActivity extends AppCompatActivity {

    PredictResponse predictResponse;
    //Firebase******
    FirebaseDatabase database;
    DatabaseReference myRef;
    ArrayList<String> list;
    RecyclerView recyclerView;
    MyCoursesAdapter myCoursesAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_my_courses);

        //databse
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("addCourses");
        recyclerView=findViewById(R.id.deletedRecyclerView);


        recyclerView =  findViewById(R.id.my_courses_recyclerview_list);
        // set a LinearLayoutManager with default vertical orientation
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);


        MyCoursesAdapter myCoursesAdapter = new MyCoursesAdapter(this, list);
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