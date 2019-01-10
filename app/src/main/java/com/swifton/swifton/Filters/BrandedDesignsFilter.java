package com.swifton.swifton.Filters;

import android.widget.Filter;

import com.swifton.swifton.Adpaters.BrandedDesignsAdapter;
import com.swifton.swifton.Models.OrderItems;

import java.util.ArrayList;
import java.util.List;

public class BrandedDesignsFilter extends Filter {
    BrandedDesignsAdapter orderAdapters;
    List<OrderItems> orderFilterList;

    public BrandedDesignsFilter(List<OrderItems> orderfilterList, BrandedDesignsAdapter orderadapter){
        this.orderAdapters = orderadapter;
        this.orderFilterList = orderfilterList;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results = new FilterResults();
        if(constraint != null && constraint.length() > 0){
            constraint= constraint.toString().toUpperCase();

            ArrayList<OrderItems> filterDesigns = new ArrayList<>();

            for(int i = 0; i<orderFilterList.size(); i++){
                String[] Titles = {"Taylor Swift - Look What You Made Me", "Bebe Rexha - Meant to Be", "Andra & Mara - Sweet Dreams", "Sam Smith - Too Good At Goodbyes "};
                String[] Description = {"By TaylorSwiftVEVO ", "By Bebe Rexha", "BySamSmithWorldVEVO", "SamSmithWorldVEVO "};
                String[] ImageUrls = {"https://cdn.pixabay.com/photo/2016/01/14/06/09/guitar-1139397_640.jpg", "https://cdn.pixabay.com/photo/2017/10/30/10/35/dance-2902034_640.jpg", "https://cdn.pixabay.com/photo/2017/09/17/11/10/luck-2758147_640.jpg", "https://cdn.pixabay.com/photo/2016/12/17/16/59/guitar-1913836_640.jpg"};
                if(orderFilterList.get(i).title.toUpperCase().contains(constraint)){
                   OrderItems Orders = new OrderItems(Titles[i],Description[i],ImageUrls[i]);
                    filterDesigns.add(orderFilterList.get(i));
                }
            }

            results.count =  orderFilterList.size();
            results.values = filterDesigns;
        }else {
            results.count = orderFilterList.size();
            results.values = orderFilterList;
        }
        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        orderAdapters.orderItems =(ArrayList<OrderItems>) results.values;
        //Refresh adapter
        orderAdapters.notifyDataSetChanged();
    }
}
