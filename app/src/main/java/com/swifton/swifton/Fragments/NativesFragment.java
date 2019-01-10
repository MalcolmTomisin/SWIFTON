package com.swifton.swifton.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mancj.materialsearchbar.MaterialSearchBar;
import com.swifton.swifton.Adpaters.TopDesignsAdapter;
import com.swifton.swifton.Helpers.Space;
import com.swifton.swifton.Models.NativeItems;
import com.swifton.swifton.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NativesFragment extends Fragment {

    TopDesignsAdapter nativeAdapter;
    List<NativeItems> nativeItems;

    public static NativesFragment newInstance() {
        NativesFragment fragment = new NativesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public NativesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_natives, container, false);

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        MaterialSearchBar nativesSearch = view.findViewById(R.id.search_Natives);
        RecyclerView recyclerViewDemo = view.findViewById(R.id.recyclerViewNatives);
        recyclerViewDemo.setLayoutManager(layoutManager);
        recyclerViewDemo.addItemDecoration(new Space(20, 1));
        recyclerViewDemo.setAdapter(new TopDesignsAdapter(feedItems(), getContext()));

        nativeAdapter = new TopDesignsAdapter(feedItems(),getContext());


//        nativesSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                nativeAdapter.getFilter().filter(newText);
//
//                return false;
//            }
//        });

        recyclerViewDemo.setItemAnimator(new DefaultItemAnimator());
        return view;

    }

    private List<NativeItems> feedItems() {
        String[] Titles = {"Taylor Swift - Look What You Made Me", "Bebe Rexha - Meant to Be", "Andra & Mara - Sweet Dreams", "Sam Smith - Too Good At Goodbyes "};
        String[] Description = {"By TaylorSwiftVEVO ", "By Bebe Rexha", "BySamSmithWorldVEVO", "SamSmithWorldVEVO "};
        String[] ImageUrls = {"https://blog.visme.co/wp-content/uploads/2017/08/40-Creative-Logo-Designs-to-Inspire-You.jpg", "https://cdn.pixabay.com/photo/2017/10/30/10/35/dance-2902034_640.jpg", "https://cdn.pixabay.com/photo/2017/09/17/11/10/luck-2758147_640.jpg", "https://cdn.pixabay.com/photo/2016/12/17/16/59/guitar-1913836_640.jpg"};
        List<NativeItems> nativeItems = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < Titles.length; j++) {
                NativeItems nativeitems = new NativeItems(Titles[j], Description[j], ImageUrls[j]);
                nativeItems.add(nativeitems);
            }
        }
        return nativeItems;
    }

}
