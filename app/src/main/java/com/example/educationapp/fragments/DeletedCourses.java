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

import com.example.educationapp.Adapters.DeletedCoursesAdapter;
import com.example.educationapp.Adapters.RecommendedCoursesAdapter;
import com.example.educationapp.Models.RecommendationItem;
import com.example.educationapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class DeletedCourses extends Fragment {
    //Firebase******
    FirebaseDatabase database;
    DatabaseReference myRef;
    ArrayList<String> list;
    RecyclerView recyclerView;
    FirebaseAuth auth;
    DeletedCoursesAdapter deletedCoursesAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_deleted_courses, container, false);
        SharedPreferences prefs = getContext().getSharedPreferences(
                "com.education.app", Context.MODE_PRIVATE);
        String email = prefs.getString("userEmail", "");
        //databse
        database = FirebaseDatabase.getInstance();
        recyclerView=view.findViewById(R.id.deletedRecyclerView);
        auth=FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();
        myRef = database.getReference(email).child("deleteCourses");

        //        // set a LinearLayoutManager with default vertical orientation
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        // Read from the database
        list = new ArrayList<>();
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String recommendationItem = dataSnapshot.getValue(String.class);
                    list.add(recommendationItem);

//                    Log.e("TAG", "onDataChange: "+recommendationItem.getCoursename().toString() );
                }
                deletedCoursesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        deletedCoursesAdapter = new DeletedCoursesAdapter(getContext(),list);
        recyclerView.setAdapter(deletedCoursesAdapter); // set the Adapter to RecyclerView


        return view;

    }
}