package com.example.educationapp.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.educationapp.Adapters.RecommendedCoursesAdapter;
import com.example.educationapp.Models.PredictResponse;
import com.example.educationapp.Models.RecommendationItem;
import com.example.educationapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class RecommendedCourses extends Fragment {

    //Firebase******
    FirebaseDatabase database;
    DatabaseReference myRef;
    ArrayList<RecommendationItem> recommendationItem;
    ArrayList<PredictResponse> list;
    RecommendedCoursesAdapter recommendedCoursesAdapter;
    RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recommended_courses, container, false);

        SharedPreferences prefs = getContext().getSharedPreferences(
                "com.education.app", Context.MODE_PRIVATE);

        String email = prefs.getString("userEmail", "");
                //databse
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference(email).child("recommendation");
        recyclerView=view.findViewById(R.id.recommendedRecyclerView);

        // Read from the database
         list = new ArrayList<>();
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {



                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                        RecommendationItem recommendationItem = dataSnapshot.getValue(RecommendationItem.class);
                        PredictResponse predictResponse = dataSnapshot.getValue(PredictResponse.class);
//                        list.add(recommendationItem);
                        list.add(predictResponse);

//                        Log.e("TAG", "onDataChange: "+recommendationItem.getCoursename().toString() );


                    }


                //        RecyclerView recyclerView =  view.findViewById(R.id.rcommended_courses_recyclerview);
                // set a LinearLayoutManager with default vertical orientation
                if(!list.isEmpty())
                {
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                    recyclerView.setLayoutManager(linearLayoutManager);
                    recommendedCoursesAdapter = new RecommendedCoursesAdapter(getContext(),(ArrayList<RecommendationItem>) list.get(0).getRecommendation());
                    recyclerView.setAdapter(recommendedCoursesAdapter); // set the Adapter to RecyclerView
                    recommendedCoursesAdapter.notifyDataSetChanged();
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        return view;
    }
}