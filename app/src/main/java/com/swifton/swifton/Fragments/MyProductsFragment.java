package com.swifton.swifton.Fragments;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.swifton.swifton.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyProductsFragment extends Fragment {

    ListView mproductList;
    CheckBox mproductCheck;
    FloatingActionButton productFab;
    ImageView deleteproduct;

    int[] displayimages ={
            R.drawable.album1,
            R.drawable.album2,
            R.drawable.album3,
            R.drawable.album4,
            R.drawable.album5,
            R.drawable.album6,
            R.drawable.album7,
            R.drawable.album8,
            R.drawable.album9,
            R.drawable.album10,
            R.drawable.album11
    };

    String[] designname = {
            "SwiftON Apple Pinky",
            "SwiftON Orange Pinky",
            "SwiftON Avocado Pinky",
            "SwiftON Blackberry Pinky",
            "SwiftON Carrot Pinky",
            "SwiftON Grape Pinky",
            "SwiftON Banana Pinky",
            "SwiftON Cherry Pinky",
            "SwiftON Pineapple Pinky",
            "SwiftON Mango Pinky",
            "SwiftON Pumpkin Pinky",
    };

    String[] desc = {
            "Apple Fruit",
            "Orange Fruit",
            "Avocado Fruit",
            "Blackberry Fruit",
            "Carrot Fruit",
            "Grape Fruit",
            "Banana Fruit",
            "Cherry Fruit",
            "Pineapple Fruit",
            "Mango Fruit",
            "Pumpkin Fruit"
    };

    String[] productType = {"Native","Babriga","Blouse","Native","Native","Jumper","Dorn","Branded Cotton","Satin Roller","Native","Sweat Shirt",};

    String[] designers = {"Pablo Fine Denims", "Sophia DFashionista", "TopNotch Designers", "Blazer Court", "Denim and Lace", "Prazy Deals", "Ziped Satin", "ladies and Gents", "Fashion Bright", "Rails On Designs", "Plural Match"};
    String[] sizes = {"44", "39","40","42","36","48","46","44","41","38","40"};
    String[] colors = {"Pink", "Crimson", "Navy Blue", "Black", "Gray", "Purple", "White", "Olive Greeb", "Royal Purple and White", "Golden Yellow", "Sea Blue"};
    String[] views = {"1000","5000", "100", "10000", "100000", "50", "300", "450", "700", "2000", "4000"};
    String[] availablecounts = {"10","2", "1", "7", "11", "2", "50", "3", "1", "5", "15"};
    String[] prizes = {
            "25000",
            "10000",
            "5000",
            "15000",
            "30000",
            "12000",
            "7000",
            "21000",
            "14000",
            "6500",
            "19999"
    };

    public MyProductsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_products, container, false);

        mproductList = view.findViewById(R.id.productList);
        mproductCheck = view.findViewById(R.id.productcheck);

        final FloatingActionButton mcartfab =  view.findViewById(R.id.addproductfab);


        customCartAdapter cartAdapter = new customCartAdapter();

        mproductList.setAdapter(cartAdapter);

        mcartfab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Thank you for petronizing us!", Snackbar.LENGTH_LONG)
                        .setAction("Buy", null).show();
            }
        });

        return view;
    }

    class customCartAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return displayimages.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View layoutView = getLayoutInflater().inflate(R.layout.productlist_layout, null);

          final ImageView  displayimage = layoutView.findViewById(R.id.displayimage);
          final TextView  productname = layoutView.findViewById(R.id.productname);
          final TextView  productdesc = layoutView.findViewById(R.id.productdesc);
          final TextView  producttype = layoutView.findViewById(R.id.producttype);
          final TextView  productdesigner = layoutView.findViewById(R.id.productdesigner);
          final TextView  productcolor = layoutView.findViewById(R.id.productcolor);
          final TextView  productprize = layoutView.findViewById(R.id.productprize);
          final TextView  productsize = layoutView.findViewById(R.id.productsize);
          final TextView  productviews = layoutView.findViewById(R.id.productviews);
          final TextView  productsavailable = layoutView.findViewById(R.id.availablecount);

            displayimage.setImageResource(displayimages[position]);
            productname.setText(designname[position]);
            productdesc.setText(desc[position]);
            producttype.setText(productType[position]);
            productdesigner.setText(designers[position]);
            productcolor.setText(colors[position]);
            productprize.setText(prizes[position]);
            productsize.setText(sizes[position]);
            productviews.setText(views[position]);
            productsavailable.setText(availablecounts[position]);
            return layoutView;
        }
    }
}
