package com.swifton.swifton.Adpaters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.squareup.picasso.Picasso;
import com.swifton.swifton.Filters.LatestDesignersFilter;
import com.swifton.swifton.Helpers.ItemClickListener;
import com.swifton.swifton.Holders.LatestDesignersHolders;
import com.swifton.swifton.Models.LatestDesigners;
import com.swifton.swifton.R;
import com.swifton.swifton.ToolBars.ToolbarAndFab;

import java.util.ArrayList;

public class LatestDesignersAdapter extends RecyclerView.Adapter<LatestDesignersHolders>implements Filterable {

    Context mContext;
    public ArrayList<LatestDesigners> latestDesigners, filterList;
    LatestDesignersFilter filter;

    public LatestDesignersAdapter(ArrayList<LatestDesigners> latestdesigners, Context context) {
        this.mContext = context;
        this.latestDesigners = latestdesigners;
        this.filterList = latestdesigners;
    }

    @Override
    public LatestDesignersHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.designers_content_layout, null);

        LatestDesignersHolders holder = new LatestDesignersHolders(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(LatestDesignersHolders holder, int position) {
        LatestDesigners latestdesignersItem = latestDesigners.get(position);
        //holder.id.setText(latestDesigners.get(position).getId());
        holder.companyname.setText(latestDesigners.get(position).getCompanyname());
        //holder.imgLogos.setImageResource(topDesigners.get(position).getLogo());
        holder.companyaddress.setText(latestDesigners.get(position).getCompanyaddress());
        holder.companycity.setText(latestDesigners.get(position).getCompanycity());
        holder.companystate.setText(latestDesigners.get(position).getCompanystate());
        holder.country.setText(latestDesigners.get(position).getCountry());
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) mContext).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        Picasso.with(mContext).load(latestdesignersItem.getLogo()).placeholder(R.drawable.ic_image_black_24dp).centerCrop().resize(displayMetrics.widthPixels, displayMetrics.heightPixels / 3).into(holder.imgLogos);

        //IMPLEMENT CLICKLISTENER

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int desc) {
                Snackbar.make(v, latestDesigners.get(desc).getCompanyname(), Snackbar.LENGTH_SHORT).show();
                confirmChoice(v, desc);
            }
        });
    }

    public void confirmChoice(View v, int desc) {
        final CharSequence[] options = {"Continue", "Not Sure!"};
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(mContext, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(mContext);
        }
        builder.setTitle("You selected " + latestDesigners.get(desc).getCompanyname());
        builder.setItems(options, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                if (options[which].equals("Continue")) {
                    Intent roomIntent = new Intent(mContext, ToolbarAndFab.class);
                    mContext.startActivity(roomIntent);
                } else if (options[which].equals("Not Sure!")) {
                    dialog.dismiss();
                }

            }
        });
        builder.show();
    }


    @Override
    public int getItemCount() {
        return latestDesigners.size();
    }

    //RETURN FILTER BJECT
    @Override
    public Filter getFilter() {

        if (filter == null) {
            filter = new LatestDesignersFilter(filterList, this);
        }
        return filter;
    }
}

