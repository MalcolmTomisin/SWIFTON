package com.swifton.swifton.Filters;

import android.widget.Filter;

import com.swifton.swifton.Adpaters.TopDesignsAdapter;
import com.swifton.swifton.Models.NativeItems;

import java.util.ArrayList;
import java.util.List;

public class TopDesignsFilter extends Filter {

    TopDesignsAdapter nativeAdapter;
    List<NativeItems> nativeList;

    public TopDesignsFilter(TopDesignsAdapter nativeadapter, List<NativeItems> nativeList){
        this.nativeAdapter = nativeadapter;
        this.nativeList = nativeList;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results = new FilterResults();
        if(constraint != null && constraint.length() > 0){
            constraint = constraint.toString().toUpperCase();

            ArrayList<NativeItems> filterNatives = new ArrayList<>();

            for(int i = 0; i<nativeList.size(); i++){
                String[] Titles = {"Taylor Swift - Look What You Made Me", "Bebe Rexha - Meant to Be", "Andra & Mara - Sweet Dreams", "Sam Smith - Too Good At Goodbyes "};
                String[] Description = {"By TaylorSwiftVEVO ", "By Bebe Rexha", "BySamSmithWorldVEVO", "SamSmithWorldVEVO "};
                String[] ImageUrls = {"https://cdn.pixabay.com/photo/2016/01/14/06/09/guitar-1139397_640.jpg", "https://cdn.pixabay.com/photo/2017/10/30/10/35/dance-2902034_640.jpg", "https://cdn.pixabay.com/photo/2017/09/17/11/10/luck-2758147_640.jpg", "https://cdn.pixabay.com/photo/2016/12/17/16/59/guitar-1913836_640.jpg"};
                if(nativeList.get(i).title.toUpperCase().contains(constraint)){
                    NativeItems natives = new NativeItems(Titles[i],Description[i],ImageUrls[i]);
                    filterNatives.add(nativeList.get(i));
                }
            }

            results.count =  nativeList.size();
            results.values = filterNatives;
        }else {
            results.count = nativeList.size();
            results.values = nativeList;
        }
        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        nativeAdapter.nativeItems = (ArrayList<NativeItems>) results.values;

        //Refresh Adapter
        nativeAdapter.notifyDataSetChanged();
    }
}
