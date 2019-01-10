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
import com.swifton.swifton.Filters.BrandedDesignersFilter;
import com.swifton.swifton.Helpers.ItemClickListener;
import com.swifton.swifton.Holders.BrandedDesignersHolders;
import com.swifton.swifton.Models.BrandedDesigners;
import com.swifton.swifton.R;
import com.swifton.swifton.ToolBars.ToolbarAndFab;

import java.util.ArrayList;

public class BrandedDesignersAdapter extends RecyclerView.Adapter<BrandedDesignersHolders>implements Filterable {

    Context mContext;
    public ArrayList<BrandedDesigners> brandedDesigners, filterList;
    BrandedDesignersFilter filter;

    public BrandedDesignersAdapter(ArrayList<BrandedDesigners> brandeddesigners, Context context) {
        this.mContext = context;
        this.brandedDesigners = brandeddesigners;
        this.filterList = brandeddesigners;
    }

    @Override
    public BrandedDesignersHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.designers_content_layout, null);

        BrandedDesignersHolders holder = new BrandedDesignersHolders(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(BrandedDesignersHolders holder, int position) {
        BrandedDesigners brandeddesignersItem = brandedDesigners.get(position);
        //holder.id.setText(latestDesigners.get(position).getId());
        holder.companyname.setText(brandedDesigners.get(position).getCompanyname());
        //holder.imgLogos.setImageResource(topDesigners.get(position).getLogo());
        holder.companyaddress.setText(brandedDesigners.get(position).getCompanyaddress());
        holder.companycity.setText(brandedDesigners.get(position).getCompanycity());
        holder.companystate.setText(brandedDesigners.get(position).getCompanystate());
        holder.country.setText(brandedDesigners.get(position).getCountry());
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) mContext).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        Picasso.with(mContext).load(brandeddesignersItem.getLogo()).placeholder(R.drawable.ic_image_black_24dp).centerCrop().resize(displayMetrics.widthPixels, displayMetrics.heightPixels / 3).into(holder.imgLogos);

        //IMPLEMENT CLICKLISTENER

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int desc) {
                Snackbar.make(v, brandedDesigners.get(desc).getCompanyname(), Snackbar.LENGTH_SHORT).show();
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
        builder.setTitle("You selected " + brandedDesigners.get(desc).getCompanyname());
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
        return brandedDesigners.size();
    }

    //RETURN FILTER BJECT
    @Override
    public Filter getFilter() {

        if (filter == null) {
            filter = new BrandedDesignersFilter(filterList, this);
        }
        return filter;
    }

}
