package com.swifton.swifton.Adpaters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.squareup.picasso.Picasso;
import com.swifton.swifton.Filters.TopDesignsFilter;
import com.swifton.swifton.Helpers.ItemClickListener;
import com.swifton.swifton.Holders.BrandedDesignsHolder;
import com.swifton.swifton.Models.NativeItems;
import com.swifton.swifton.R;

import java.util.List;

public class TopDesignsAdapter extends RecyclerView.Adapter implements Filterable {
    Bundle Title, Description, Backdrop;
    public List<NativeItems> nativeItems;
    List<NativeItems> filterList;
    Context mContext;
    TopDesignsFilter nativeFilter;
    int Desc;

    public TopDesignsAdapter(List<NativeItems> nativeitems, Context context){
        this.nativeItems = nativeitems;
        this.mContext = context;
        filterList = nativeitems;
    }

    @NonNull
    @Override
    public BrandedDesignsHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mContext);
        View row=inflater.inflate(R.layout.custom_row_demo, parent, false);
        return new BrandedDesignsHolder(row);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        NativeItems currentDemoItem = nativeItems.get(position);
        BrandedDesignsHolder nativeviewHolder = (BrandedDesignsHolder) holder;
        nativeviewHolder.Title.setText(currentDemoItem.title);
        nativeviewHolder.Description.setText(currentDemoItem.Description);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) mContext).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        Picasso.with(mContext).load(currentDemoItem.imageUrl).placeholder(R.drawable.ic_image_black_24dp).centerCrop().resize(displayMetrics.widthPixels, displayMetrics.heightPixels / 3).into(nativeviewHolder.Thumbnail);

        nativeviewHolder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int desc) {
                Snackbar.make(v, nativeItems.get(desc).title, Snackbar.LENGTH_SHORT).show();
                confirmChoice(v, desc);
            }
        });
    }

    public void confirmChoice(View view, final int desc) {
        final CharSequence[] options = { "Purchase Ticket","Make Inquiery","Visit Event Website", "Not Sure" };
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(mContext, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(mContext);
        }
        builder.setTitle("I will attend this " + nativeItems.get(desc).title + " ?" );
        builder.setItems(options,new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                if(options[which].equals("Purchase Ticket"))
                {
                    //Intent DetailsIntent = new Intent(c, EventsDetailsActivity.class);
                    Title= new Bundle(); Description =  new Bundle(); Backdrop = new Bundle();
                    Title.putString("title",nativeItems.get(desc).title);
                    Description.putString("venue", nativeItems.get(desc).Description);
                    Backdrop.putString("date", nativeItems.get(desc).imageUrl);
                    //Hbanner.putInt("banner", halls.get(desc).getBanner());

                       /* DetailsIntent.putExtras(HName);
                        DetailsIntent.putExtras(HAddress);
                        DetailsIntent.putExtras(HLocation);
                        DetailsIntent.putExtras(Hbanner);
                        c.startActivity(DetailsIntent);*/
                }
                else if (options[which].equals("Make Inquiery")) {

                    dialog.dismiss();
                }
                else if (options[which].equals("Visit Event Website")) {
                    Bundle url = new Bundle();
                    url.putString("URL", nativeItems.get(desc).imageUrl);
                    String site = nativeItems.get(desc).imageUrl;
                    final Intent weburlIntent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(site));
                    weburlIntent.putExtras(url);
                    mContext.startActivity(weburlIntent);
                    dialog.dismiss();
                } else if (options[which].equals("Not Sure")) {
                    dialog.dismiss();
                }

            }
        });
        builder.show();
    }


    @Override
    public int getItemCount() {
        return nativeItems.size();
    }

    //RETURN FILTER BJECT
    @Override
    public Filter getFilter() {

        if(nativeFilter == null){
            nativeFilter = new TopDesignsFilter(this, filterList);
        }
        return nativeFilter;
    }
}
