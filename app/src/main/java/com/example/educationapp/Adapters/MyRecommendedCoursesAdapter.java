package com.example.educationapp.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.educationapp.Models.PredictResponse;
import com.example.educationapp.Models.RecommendationItem;
import com.example.educationapp.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class MyRecommendedCoursesAdapter extends RecyclerView.Adapter<MyRecommendedCoursesAdapter.MyViewHolder> {

    FirebaseDatabase database;
    DatabaseReference myRef,addRef;
    Context context;
    PredictResponse predictResponse;
    int count;

    public MyRecommendedCoursesAdapter(Context context, PredictResponse predictResponse, int count) {
        this.context = context;
        this.predictResponse = predictResponse;
        this.count = count;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recommended_course_recyclerview, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v); // pass the view to View Holder
        return vh;
    }



    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        database = FirebaseDatabase.getInstance();
        SharedPreferences prefs = context.getSharedPreferences(
                "com.education.app", Context.MODE_PRIVATE);

        String email = prefs.getString("userEmail", "");

        // Write a message to the database
        myRef = database.getReference(email).child("deleteCourses").push();
        addRef = database.getReference(email).child("addCourses").push();

        String courseName = predictResponse.getRecommendation().get(position).getCoursename();
        String rating = predictResponse.getRecommendation().get(position).getRating();
        String price = predictResponse.getRecommendation().get(position).getPrice();

        holder.courseName.setText(courseName);
        holder.rating.setText(rating);
        holder.price.setText(price);

        //delete Recommendation
        holder.deleteRecommendation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(context, "Course remove successfully", Toast.LENGTH_SHORT).show();
                myRef.setValue(courseName);


            }
        });
        holder.addRecommendation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Course add successfully", Toast.LENGTH_SHORT).show();

                addRef.setValue(courseName);

            }
        });




        // implement setOnClickListener event on item view.
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }


    @Override
    public int getItemCount() {
        return count;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        // init the item view's
        TextView courseName,rating,price;
        ImageView addRecommendation,deleteRecommendation;
        public MyViewHolder(View itemView) {
            super(itemView);

            courseName=itemView.findViewById(R.id.courseName);
            rating=itemView.findViewById(R.id.courseRating);
            price=itemView.findViewById(R.id.coursePrice);
            addRecommendation=itemView.findViewById(R.id.addRecommendation);
            deleteRecommendation=itemView.findViewById(R.id.deleteRecommendation);
        }
    }
}
