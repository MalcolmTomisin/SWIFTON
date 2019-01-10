package com.swifton.swifton.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mancj.materialsearchbar.MaterialSearchBar;
import com.swifton.swifton.Adpaters.BrandedDesignsAdapter;
import com.swifton.swifton.Helpers.Space;
import com.swifton.swifton.Models.OrderItems;
import com.swifton.swifton.R;

import java.util.ArrayList;
import java.util.List;

import static com.swifton.swifton.R.id.recyclerViewMyOrders;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyOrdersFragment extends Fragment {
    BrandedDesignsAdapter orderadapter;
    List<OrderItems> data_list;

    public static MyOrdersFragment newInstance() {
        MyOrdersFragment fragment = new MyOrdersFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    public MyOrdersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_orders, container, false);

        data_list = new ArrayList<>();
        orderadapter = new BrandedDesignsAdapter(data_list, getActivity());

        MaterialSearchBar myordersSearch = view.findViewById(R.id.search_MyOrders);
        final RecyclerView recyclerViewOrders = view.findViewById(recyclerViewMyOrders);
        recyclerViewOrders.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewOrders.addItemDecoration(new Space(20, 1));
        recyclerViewOrders.setAdapter(new BrandedDesignsAdapter(feedItems(), getContext()));

        orderadapter = new BrandedDesignsAdapter(feedItems(),getContext());

        myordersSearch.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        myordersSearch.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {


                if (!enabled)
                    recyclerViewOrders.setAdapter(new BrandedDesignsAdapter(feedItems(), getContext()));
            }

            @Override
            public void onSearchConfirmed(CharSequence text) {
                orderadapter.getFilter().filter(text);
            }

            @Override
            public void onButtonClicked(int buttonCode) {

            }
        });

        recyclerViewOrders.setItemAnimator(new DefaultItemAnimator());
        return view;
    }

    private List<OrderItems> feedItems() {
        String[] Titles = {"Taylor Swift - Look What You Made Me", "Bebe Rexha - Meant to Be", "Andra & Mara - Sweet Dreams", "Sam Smith - Too Good At Goodbyes "};
        String[] Description = {"By TaylorSwiftVEVO ", "By Bebe Rexha", "BySamSmithWorldVEVO", "SamSmithWorldVEVO "};
        String[] ImageUrls = {"https://cdn.pixabay.com/photo/2016/01/14/06/09/guitar-1139397_640.jpg", "https://cdn.pixabay.com/photo/2017/10/30/10/35/dance-2902034_640.jpg", "https://cdn.pixabay.com/photo/2017/09/17/11/10/luck-2758147_640.jpg", "https://cdn.pixabay.com/photo/2016/12/17/16/59/guitar-1913836_640.jpg"};
        List<OrderItems> orderItems = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < Titles.length; j++) {
                OrderItems orderitems = new OrderItems(Titles[j], Description[j], ImageUrls[j]);
                orderItems.add(orderitems);
            }
        }
        return orderItems;
    }

}
