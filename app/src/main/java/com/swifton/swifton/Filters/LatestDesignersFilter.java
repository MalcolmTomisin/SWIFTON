package com.swifton.swifton.Filters;

import android.widget.Filter;

import com.swifton.swifton.Adpaters.LatestDesignersAdapter;
import com.swifton.swifton.Models.LatestDesigners;

import java.util.ArrayList;

public class LatestDesignersFilter extends Filter {
    LatestDesignersAdapter Adapter;
    ArrayList<LatestDesigners> filterList;

    public LatestDesignersFilter(ArrayList<LatestDesigners> filterList, LatestDesignersAdapter adapter){
        this.Adapter = adapter;
        this.filterList = filterList;

    }

    @Override
    protected FilterResults performFiltering(CharSequence charSequence) {
        FilterResults results = new FilterResults();
        if(charSequence != null && charSequence.length() > 0){
            charSequence = charSequence.toString().toUpperCase();

            ArrayList<LatestDesigners> filterTopdesigners = new ArrayList<>();

            for(int i = 0; i<filterList.size(); i++){
                if(filterList.get(i).getCompanyname().toUpperCase().contains(charSequence)){
                    LatestDesigners LDs = new LatestDesigners();
                    filterTopdesigners.add(filterList.get(i));
                }
            }

            results.count =  filterList.size();
            results.values = filterTopdesigners;
        }else {
            results.count = filterList.size();
            results.values = filterList;
        }
        return results;
    }

    @Override
    protected void publishResults(CharSequence charSequence, FilterResults results) {
        Adapter.latestDesigners = (ArrayList<LatestDesigners>) results.values;

        //REFRESH
        Adapter.notifyDataSetChanged();
    }
}
