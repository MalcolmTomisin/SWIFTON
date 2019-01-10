package com.swifton.swifton.Filters;

import android.widget.Filter;

import com.swifton.swifton.Adpaters.TopDesignersAdapter;
import com.swifton.swifton.Models.TopDesigners;

import java.util.ArrayList;

public class TopDesginersFilter extends Filter {
    TopDesignersAdapter Adapter;
    ArrayList<TopDesigners> filterList;

    public TopDesginersFilter(ArrayList<TopDesigners> filterList, TopDesignersAdapter adapter){
        this.Adapter = adapter;
        this.filterList = filterList;

    }

    @Override
    protected FilterResults performFiltering(CharSequence charSequence) {
        FilterResults results = new FilterResults();
        if(charSequence != null && charSequence.length() > 0){
            charSequence = charSequence.toString().toUpperCase();

            ArrayList<TopDesigners> filterTopdesigners = new ArrayList<>();

            for(int i = 0; i<filterList.size(); i++){
                if(filterList.get(i).getCompanyname().toUpperCase().contains(charSequence)){
                    TopDesigners TDs = new TopDesigners();
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
        Adapter.topDesigners = (ArrayList<TopDesigners>) results.values;

        //REFRESH
        Adapter.notifyDataSetChanged();
    }
}

