package com.moringaschool.mobilelibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.moringaschool.mobilelibrary.adapters.BookPagerAdapter;
import com.moringaschool.mobilelibrary.model.Item;
import com.moringaschool.mobilelibrary.model.VolumeInfo;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BookDetailActivity extends AppCompatActivity {
    @BindView(R.id.viewPager)
    ViewPager mPager;
    BookPagerAdapter mAdapter;

    List<Item> books;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        ButterKnife.bind(this);

        books = Parcels.unwrap(getIntent().getParcelableExtra("books"));
        int startingPosition = getIntent().getIntExtra("position", 0);
        mAdapter = new BookPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, books);
        mPager.setAdapter(mAdapter);
        mPager.setCurrentItem(startingPosition);


    }
}
