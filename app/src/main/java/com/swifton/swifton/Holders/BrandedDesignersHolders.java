package com.swifton.swifton.Holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.swifton.swifton.Helpers.ItemClickListener;
import com.swifton.swifton.R;

public class BrandedDesignersHolders  extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView id, approvalstatus, level, zipcode, companyname, companyregno, companyaddress, country,
            companystate, companycity,emailaddress, phone, website, companycode, verified, created_at;
    public ImageView imgLogos;

    ItemClickListener itemClickListener;

    public BrandedDesignersHolders(View itemView){
        super(itemView);

        this.imgLogos =  itemView.findViewById(R.id.companyLogo);
        this.companyname =  itemView.findViewById(R.id.companyName);
        this.companyaddress =  itemView.findViewById(R.id.companyAddress);
        this.companycity =  itemView.findViewById(R.id.companyCity);
        this.companystate =  itemView.findViewById(R.id.companyState);
        this.country =  itemView.findViewById(R.id.companyCountry);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        this.itemClickListener.onItemClick(view, getLayoutPosition());

    }

    public void  setItemClickListener(ItemClickListener icl){
        this.itemClickListener = icl;
    }
}
