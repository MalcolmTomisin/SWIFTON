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
import com.swifton.swifton.Adpaters.LatestDesignsAdapter;
import com.swifton.swifton.Helpers.Space;
import com.swifton.swifton.Models.LatestDesignsItems;
import com.swifton.swifton.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllDesignsFragment extends Fragment {

    private List<LatestDesignsItems> data_list;
    private LatestDesignsAdapter adapter;

    public static AllDesignsFragment newInstance() {
        AllDesignsFragment fragment = new AllDesignsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public AllDesignsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all_designs, container, false);

        //data_list = new ArrayList<LatestDesignsItems>(data_list);

        MaterialSearchBar designsSearch = view.findViewById(R.id.search_allDesigns);
        RecyclerView recyclerViewDemo = view.findViewById(R.id.recyclerViewAllDesigns);
        recyclerViewDemo.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewDemo.addItemDecoration(new Space(20, 1));
        recyclerViewDemo.setAdapter(new LatestDesignsAdapter(feedItems(), getContext()));


        adapter = new LatestDesignsAdapter(feedItems(),getContext());

        //load_data_from_server(0);

//        recyclerViewDemo.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                if(LinearLayoutManager.findLastCompletelyVisibleItemPosition() == data_list.size()-1){
//                    load_data_from_server(data_list.get(data_list.size()-1).title);
//                }
//            }
//        });

//        designsSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//
//                if(adapter!= null){
//                    adapter.getFilter().filter(newText);
//                }
//                return false;
//            }
//        });

        recyclerViewDemo.setItemAnimator(new DefaultItemAnimator());

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
//   static private void load_data_from_server(String id) {
//
//        AsyncTask<Integer,Void,Void> task = new AsyncTask<Integer, Void, Void>() {
//            @Override
//            protected Void doInBackground(Integer... integers) {
//
//                OkHttpClient client = new OkHttpClient();
//                Request request = new Request.Builder()
//                        .url("http://192.168.8.102/gotickets.com/get_event_details.php?id="+integers[0])
//                        .build();
//                try {
//                    Response response = client.newCall(request).execute();
//
//                    JSONArray array = new JSONArray(response.body().string());
//
//                    for (int i=0; i<array.length(); i++){
//
//                        JSONObject object = array.getJSONObject(i);
//
//                        //Events data = new Events(
//                        LatestDesignsItems data = new LatestDesignsItems(
//                                object.getString("title"),
//                                object.getString("cescription"),
//                                object.getString("imageurl"));
//                        data_list.add(data);
//                    }
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                } catch (JSONException e) {
//                    System.out.println("End of content");
//                }
//                return null;
//            }
//
//            @Override
//            protected void onPostExecute(Void aVoid) {
//                adapter.notifyDataSetChanged();
//            }
//        };
//
//        task.execute(Integer.valueOf(id));
//    }

}
