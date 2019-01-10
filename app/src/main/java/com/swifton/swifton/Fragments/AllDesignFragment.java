package com.swifton.swifton.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.swifton.swifton.Adpaters.LatestDesignsAdapter;
import com.swifton.swifton.Helpers.Space;
import com.swifton.swifton.Models.LatestDesignsItems;
import com.swifton.swifton.R;

import java.util.ArrayList;
import java.util.List;


public class AllDesignFragment extends Fragment {


    public AllDesignFragment() {
    }


    public static AllDesignFragment newInstance() {
        AllDesignFragment fragment = new AllDesignFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_design, container, false);
        RecyclerView recyclerViewDemo = view.findViewById(R.id.recyclerViewAllDesigns);
        recyclerViewDemo.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewDemo.addItemDecoration(new Space(20, 1));
        recyclerViewDemo.setAdapter(new LatestDesignsAdapter(feedItems(), getContext()));
        return view;
    }

    private List<LatestDesignsItems> feedItems() {
        String[] Titles = {"Taylor Swift - Look What You Made Me", "Bebe Rexha - Meant to Be", "Andra & Mara - Sweet Dreams", "Sam Smith - Too Good At Goodbyes "};
        String[] Description = {"By TaylorSwiftVEVO ", "By Bebe Rexha", "BySamSmithWorldVEVO", "SamSmithWorldVEVO "};
        String[] ImageUrls = {"https://cdn.pixabay.com/photo/2016/01/14/06/09/guitar-1139397_640.jpg", "https://cdn.pixabay.com/photo/2017/10/30/10/35/dance-2902034_640.jpg", "https://cdn.pixabay.com/photo/2017/09/17/11/10/luck-2758147_640.jpg", "https://cdn.pixabay.com/photo/2016/12/17/16/59/guitar-1913836_640.jpg"};
        List<LatestDesignsItems> latestDesignsItems = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < Titles.length; j++) {
                LatestDesignsItems designItems = new LatestDesignsItems(Titles[j], Description[j], ImageUrls[j]);
                latestDesignsItems.add(designItems);
            }
        }
        return latestDesignsItems;
    }

}
