package com.example.educationapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.educationapp.R;


public class MyRecommendedCoursesAdapter extends RecyclerView.Adapter<MyRecommendedCoursesAdapter.MyViewHolder> {



    Context context;
    private int serialCount =1;
    int count;


    public MyRecommendedCoursesAdapter(Context context, int count ) {

        this.context = context;
        this.count = count;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_recommended_course_recyclerview, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v); // pass the view to View Holder
        return vh;
    }



    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {



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
        TextView serialNo,name;
        ImageView image;


        public MyViewHolder(View itemView) {
            super(itemView);

        }
    }
}
