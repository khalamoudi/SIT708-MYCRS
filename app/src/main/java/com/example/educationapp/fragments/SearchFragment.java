package com.example.educationapp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.educationapp.Adapters.MyCoursesAdapter;
import com.example.educationapp.Adapters.MyRecommendedCoursesAdapter;
import com.example.educationapp.R;


public class SearchFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.my_courses_recyclerview_search);
        // set a LinearLayoutManager with default vertical orientation
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

//
//        MyCoursesAdapter myCoursesAdapter = new MyCoursesAdapter(getContext(),4);
//        recyclerView.setAdapter(myCoursesAdapter); // set the Adapter to RecyclerView


        RecyclerView recyclerView1 = (RecyclerView) view.findViewById(R.id.add_recommended_recyclerview_search);
        // set a LinearLayoutManager with default vertical orientation
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView1.setLayoutManager(linearLayoutManager1);

//
//        MyRecommendedCoursesAdapter myRecommendedCoursesAdapter = new MyRecommendedCoursesAdapter(getContext(),4);
//        recyclerView1.setAdapter(myRecommendedCoursesAdapter); // set the Adapter to RecyclerView
//

        return view;
    }
}