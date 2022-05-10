package com.example.educationapp.fragments;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.educationapp.Adapters.RecommendedCoursesAdapter;
import com.example.educationapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HistoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HistoryFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HistoryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HistoryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HistoryFragment newInstance(String param1, String param2) {
        HistoryFragment fragment = new HistoryFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        CardView deleteActive , recommendedActive;
        TextView deleteActiveText  , recommendedActiveText;
        deleteActive = (CardView) view.findViewById(R.id.delete_active_card);

        recommendedActive = (CardView) view.findViewById(R.id.recommended_active_card);

        deleteActiveText = (TextView) view.findViewById(R.id.delete_active_card_text);
        recommendedActiveText = (TextView)view.findViewById(R.id.recommended_active_card_text);




        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rcommended_courses_recyclerview);
        // set a LinearLayoutManager with default vertical orientation
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);


         RecommendedCoursesAdapter recommendedCoursesAdapter = new RecommendedCoursesAdapter(getContext(),4);
        recyclerView.setAdapter(recommendedCoursesAdapter); // set the Adapter to RecyclerView


        RecyclerView recyclerView1 = (RecyclerView) view.findViewById(R.id.deleted_courses_recyclerview);
        // set a LinearLayoutManager with default vertical orientation
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getContext());
        recyclerView1.setLayoutManager(linearLayoutManager1);


        RecommendedCoursesAdapter deletedCoursesAdapter = new RecommendedCoursesAdapter(getContext(),1);
        recyclerView1.setAdapter(deletedCoursesAdapter); // set the Adapter to RecyclerView



        deleteActiveText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteActive.setVisibility(View.GONE);
                recommendedActive.setVisibility(View.VISIBLE);
                recyclerView1.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);

            }
        });
        recommendedActiveText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recommendedActive.setVisibility(View.GONE);
                deleteActive.setVisibility(View.VISIBLE);

                recyclerView.setVisibility(View.GONE);
                recyclerView1.setVisibility(View.VISIBLE);



            }
        });


        return  view;

    }
}