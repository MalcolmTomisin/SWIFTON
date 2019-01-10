package com.swifton.swifton.ToolBars;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.swifton.swifton.Fragments.AllDesignsFragment;
import com.swifton.swifton.Fragments.MyOrdersFragment;
import com.swifton.swifton.Fragments.NativesFragment;
import com.swifton.swifton.R;
import com.swifton.swifton.ViewPagerAdapter;

public class CollapsingToolbarTabs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapsing_toobar_tabs);
        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab =  findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        ViewPager mViewPager = findViewById(R.id.viewpager);
        ViewPagerAdapter mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPagerAdapter.addFragment(AllDesignsFragment.newInstance(), "Latest Designs");
        mViewPagerAdapter.addFragment(NativesFragment.newInstance(), "Top Designs");
        mViewPagerAdapter.addFragment(MyOrdersFragment.newInstance(), "Branded Designs");
        mViewPager.setAdapter(mViewPagerAdapter);

        TabLayout tabLayout =  findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        ImageView imageViewMusic = findViewById(R.id.imaViewMusic);
        Picasso.with(this).load("https://cdn.pixabay.com/photo/2015/04/13/13/37/dj-720589_640.jpg").fit().into(imageViewMusic);
    }

}
