package com.swifton.swifton.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.swifton.swifton.Adpaters.BrandedDesignsAdapter;
import com.swifton.swifton.Models.OrderItems;
import com.swifton.swifton.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DesignersFragment extends Fragment {

    BrandedDesignsAdapter orderadapter;
    List<OrderItems> data_list;

    public static MyOrdersFragment newInstance() {
        MyOrdersFragment fragment = new MyOrdersFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public DesignersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_designers, container, false);
    }

}
