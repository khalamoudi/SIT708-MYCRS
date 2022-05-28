package com.example.educationapp.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.educationapp.fragments.DeletedCourses;
import com.example.educationapp.fragments.RecommendedCourses;

public class PageAdapter extends FragmentStatePagerAdapter {
    private static int size = 2;

    public PageAdapter(@NonNull FragmentManager fm, int tabCount) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                    fragment = new RecommendedCourses();
                    break;

            case 1:
                    fragment = new DeletedCourses();
                    break;
        }
        return fragment;
    }
        @Override
        public int getCount () {
            return size;
        }
    }


