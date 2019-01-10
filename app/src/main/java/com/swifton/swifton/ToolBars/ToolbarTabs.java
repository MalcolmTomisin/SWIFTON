package com.swifton.swifton.ToolBars;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.swifton.swifton.Fragments.DesignersFragment;
import com.swifton.swifton.Fragments.MyOrdersFragment;
import com.swifton.swifton.Fragments.TopDesignersFragment;
import com.swifton.swifton.R;
import com.swifton.swifton.ViewPagerAdapter;

public class ToolbarTabs extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar_tabs);
        Toolbar toolbar = findViewById(R.id.toolbardesigners);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab =  findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        ViewPager mViewPager = findViewById(R.id.viewpagerDesigners);
        ViewPagerAdapter mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPagerAdapter.addFragment(DesignersFragment.newInstance(), "Latest Designers");
        mViewPagerAdapter.addFragment(TopDesignersFragment.newInstance(), "Top Designers");
        mViewPagerAdapter.addFragment(MyOrdersFragment.newInstance(), "Brand Designers");
        mViewPager.setAdapter(mViewPagerAdapter);
        TabLayout mTabLayout = findViewById(R.id.tabsdesigners);
        mTabLayout.setupWithViewPager(mViewPager);

    }

}
