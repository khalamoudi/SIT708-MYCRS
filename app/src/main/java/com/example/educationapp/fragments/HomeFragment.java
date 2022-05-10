package com.example.educationapp.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.educationapp.Adapters.MyCoursesAdapter;
import com.example.educationapp.Adapters.MyRecommendedCoursesAdapter;
import com.example.educationapp.Adapters.RecommendedCoursesAdapter;
import com.example.educationapp.BotRecommendationActivity;
import com.example.educationapp.MyCoursesActivity;
import com.example.educationapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        TextView movetoMyCourses , assistentText;
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);



        assistentText = (TextView) view.findViewById(R.id.assistent_act_text);
        movetoMyCourses = (TextView) view.findViewById(R.id.movetoMycourses);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.my_courses_recyclerview);
        // set a LinearLayoutManager with default vertical orientation
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);


        MyCoursesAdapter myCoursesAdapter = new MyCoursesAdapter(getContext(),4);
        recyclerView.setAdapter(myCoursesAdapter); // set the Adapter to RecyclerView


        RecyclerView recyclerView1 = (RecyclerView) view.findViewById(R.id.add_recommended_recyclerview);
        // set a LinearLayoutManager with default vertical orientation
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView1.setLayoutManager(linearLayoutManager1);


        MyRecommendedCoursesAdapter myRecommendedCoursesAdapter = new MyRecommendedCoursesAdapter(getContext(),4);
        recyclerView1.setAdapter(myRecommendedCoursesAdapter); // set the Adapter to RecyclerView


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