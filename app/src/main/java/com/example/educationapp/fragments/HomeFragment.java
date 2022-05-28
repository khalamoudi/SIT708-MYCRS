package com.example.educationapp.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
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
import android.widget.TextView;

import com.example.educationapp.Adapters.MyCoursesAdapter;
import com.example.educationapp.Adapters.RecommendedCoursesAdapter;
import com.example.educationapp.BotRecommendationActivity;
import com.example.educationapp.Models.PredictResponse;
import com.example.educationapp.Models.RecommendationItem;
import com.example.educationapp.MyCoursesActivity;
import com.example.educationapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeFragment extends Fragment {


    //Firebase******
    FirebaseDatabase database;
    DatabaseReference myRef1;
    DatabaseReference myRef2;
    ArrayList<String> list;
    ArrayList<PredictResponse> predictResponseList;
    RecyclerView recyclerView;
    MyCoursesAdapter myCoursesAdapter;
    String email = "";
    String currentFirebaseUser ;
    TextView userName;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {


        SharedPreferences prefs = getContext().getSharedPreferences(
                "com.education.app", Context.MODE_PRIVATE);

        email = prefs.getString("userEmail", "");

        TextView movetoMyCourses , assistentText;
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        userName=view.findViewById(R.id.userRegisterName);
        //databse
        database = FirebaseDatabase.getInstance();
        myRef1 = database.getReference(email).child("addCourses");
        //get the name of user
      currentFirebaseUser  = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();
      userName.setText("Welcome Back "+currentFirebaseUser);





        assistentText = (TextView) view.findViewById(R.id.assistent_act_text);
        movetoMyCourses = (TextView) view.findViewById(R.id.movetoMycourses);

        //my courses recycler view
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.my_courses_recyclerview);
        // set a LinearLayoutManager with default vertical orientation
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

        // Read from the database
        list = new ArrayList<>();
        myRef1.addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                    RecommendationItem recommendationItem = dataSnapshot.getValue(RecommendationItem.class);
                    String myCourse = dataSnapshot.getValue(String.class);
                    list.add(myCourse);

                    Log.e("TAG", "onDataChange: "+myCourse);
                }
                myCoursesAdapter = new MyCoursesAdapter(getContext(), list);
                recyclerView.setAdapter(myCoursesAdapter);
                myCoursesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        myRef2 = database.getReference(email).child("recommendation");
        RecyclerView recyclerView1 = view.findViewById(R.id.add_recommended_recyclerview);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView1.setLayoutManager(linearLayoutManager1);

        // Read from the database
        list = new ArrayList<>();
        myRef2.addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                predictResponseList = new ArrayList<>();

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                        RecommendationItem recommendationItem = dataSnapshot.getValue(RecommendationItem.class);
                    PredictResponse predictResponse = dataSnapshot.getValue(PredictResponse.class);
//                        list.add(recommendationItem);
                    predictResponseList.add(predictResponse);

//                        Log.e("TAG", "onDataChange: "+recommendationItem.getCoursename().toString() );


                }


                RecyclerView recyclerView =  view.findViewById(R.id.add_recommended_recyclerview);
//                 set a LinearLayoutManager with default vertical orientation

                if(list.isEmpty())
                {
                    return;
                }


                RecommendedCoursesAdapter recommendedCoursesAdapter = new RecommendedCoursesAdapter(getContext(),(ArrayList<RecommendationItem>) predictResponseList.get(0).getRecommendation());
                recyclerView1.setAdapter(recommendedCoursesAdapter); // set the Adapter to RecyclerView
                recommendedCoursesAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        DatabaseReference refUser = database.getReference(email).child("user").child("userFullName");
        refUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String name = snapshot.getValue(String.class);
                userName.setText("Welcome Back "+name);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        movetoMyCourses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MyCoursesActivity.class);
                startActivity(intent);
            }
        });

        assistentText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), BotRecommendationActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }


}