package com.swifton.swifton.Holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.swifton.swifton.Helpers.ItemClickListener;
import com.swifton.swifton.R;

public class BrandedDesignsHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    ItemClickListener itemClickListener;
   public ImageView Thumbnail;
   public TextView Title, Description;

    public BrandedDesignsHolder(View itemView){
        super(itemView);

        Thumbnail = itemView.findViewById(R.id.imageViewThumbnail);
        Title = itemView.findViewById(R.id.textViewTitle);
        Description = itemView.findViewById(R.id.textViewDes);

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
