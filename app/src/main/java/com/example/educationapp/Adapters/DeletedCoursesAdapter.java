package com.example.educationapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.educationapp.Models.RecommendationItem;
import com.example.educationapp.R;

import java.util.ArrayList;


public class DeletedCoursesAdapter extends RecyclerView.Adapter<DeletedCoursesAdapter.MyViewHolder> {

    Context context;
    ArrayList<String> deleteItem;

    public DeletedCoursesAdapter(Context context, ArrayList<String> deleteItem) {
        this.context = context;
        this.deleteItem = deleteItem;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.deleted_coursee_list, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v); // pass the view to View Holder
        return vh;
    }



    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        String user=deleteItem.get(position);
        holder.courseName.setText(user);
//        holder.rating.setText(user.getRating());
//        holder.price.setText(user.getPrice());
        // implement setOnClickListener event on item view.
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }


    @Override
    public int getItemCount() {
        return deleteItem.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        // init the item view's
        TextView courseName,rating,price;
        public MyViewHolder(View itemView) {
            super(itemView);

            courseName = itemView.findViewById(R.id.courseName);
//            rating = itemView.findViewById(R.id.courseRating);
//            price = itemView.findViewById(R.id.coursePrice);

        }
    }
}
