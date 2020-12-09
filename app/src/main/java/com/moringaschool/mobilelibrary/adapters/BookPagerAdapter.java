package com.moringaschool.mobilelibrary.adapters;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.moringaschool.mobilelibrary.BookDetailFragment;
import com.moringaschool.mobilelibrary.model.VolumeInfo;

import java.util.ArrayList;

public class BookPagerAdapter extends FragmentPagerAdapter {

    private static final String TAG = " ";
    private ArrayList<VolumeInfo> bookResults;


    public BookPagerAdapter(FragmentManager fm, int behavior, ArrayList<VolumeInfo> books){
        super(fm, behavior);
        bookResults = books;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        return BookDetailFragment.newInstance(bookResults.get(position));
    }

    @Override
    public int getCount() {
        Log.d(TAG, "getCount: "+ bookResults);
        return bookResults.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return bookResults.get(position).getPublisher();
    }
}


