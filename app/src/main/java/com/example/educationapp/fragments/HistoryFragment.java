package com.example.educationapp.fragments;

import android.os.Bundle;


import androidx.fragment.app.Fragment;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.educationapp.Adapters.PageAdapter;
import com.example.educationapp.Adapters.RecommendedCoursesAdapter;
import com.example.educationapp.Models.RecommendationItem;
import com.example.educationapp.R;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;


public class HistoryFragment extends Fragment {

    //tab Layout
    TabLayout tabLayout;
    TabItem tabItem1,tabItem2;
    ViewPager viewPager;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        tabLayout=view.findViewById(R.id.tablayout);
        viewPager=view.findViewById(R.id.simpleViewPager);
        tabItem1=view.findViewById(R.id.tab1);
        tabItem2=view.findViewById(R.id.tab2);


        PagerAdapter pagerAdapter=new PageAdapter(getChildFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);


        //on tab click listeners*******************
        tabLayout.setOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                viewPager.setCurrentItem(tab.getPosition());

                if(tab.getPosition()==0 || tab.getPosition()==1 )
                {
                    pagerAdapter.notifyDataSetChanged();
                }
                Fragment fragment=null;

                switch(tab.getPosition())
                {
                    case 0:
                        fragment = new RecommendedCourses();
                        break;
                    case 1:
                        fragment = new DeletedCourses();
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}
            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        return  view;
    }
}







































//
//        CardView deleteActive , recommendedActive;
//        TextView deleteActiveText  , recommendedActiveText;
//        deleteActive = (CardView) view.findViewById(R.id.delete_active_card);
//
//        recommendedActive = (CardView) view.findViewById(R.id.recommended_active_card);
//
//        deleteActiveText =  view.findViewById(R.id.delete_active_card_text);
//        recommendedActiveText = view.findViewById(R.id.recommended_active_card_text);
//        //databse
//        database = FirebaseDatabase.getInstance();
//        myRef = database.getReference("recommended_Courses").child("recommendation");
//
//        // Read from the database
//         list = new ArrayList<>();
//        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
//            @SuppressLint("NotifyDataSetChanged")
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//
//                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                        RecommendationItem recommendationItem = dataSnapshot.getValue(RecommendationItem.class);
//                        list.add(recommendationItem);
//
//                        Log.e("TAG", "onDataChange: "+recommendationItem.getCoursename().toString() );
//                    }
//                    recommendedCoursesAdapter.notifyDataSetChanged();
//
//
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//
//
//        RecyclerView recyclerView =  view.findViewById(R.id.rcommended_courses_recyclerview);
//        // set a LinearLayoutManager with default vertical orientation
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
//        recyclerView.setLayoutManager(linearLayoutManager);
//
//
//         recommendedCoursesAdapter = new RecommendedCoursesAdapter(getContext(),list);
//        recyclerView.setAdapter(recommendedCoursesAdapter); // set the Adapter to RecyclerView
//
//
//        RecyclerView recyclerView1 = (RecyclerView) view.findViewById(R.id.deleted_courses_recyclerview);
//        // set a LinearLayoutManager with default vertical orientation
//        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getContext());
//        recyclerView1.setLayoutManager(linearLayoutManager1);
//
//
//        RecommendedCoursesAdapter deletedCoursesAdapter = new RecommendedCoursesAdapter(getContext(),list);
//        recyclerView1.setAdapter(deletedCoursesAdapter); // set the Adapter to RecyclerView
//
//
//
//        deleteActiveText.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                deleteActive.setVisibility(View.GONE);
//                recommendedActive.setVisibility(View.VISIBLE);
//                recyclerView1.setVisibility(View.GONE);
//                recyclerView.setVisibility(View.VISIBLE);
//
//            }
//        });
//        recommendedActiveText.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                recommendedActive.setVisibility(View.GONE);
//                deleteActive.setVisibility(View.VISIBLE);
//                recyclerView.setVisibility(View.GONE);
//                recyclerView1.setVisibility(View.VISIBLE);
//
//            }
//        });
//
