package com.scorpion.mealtohome.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.scorpion.mealtohome.R;

public class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView tvCategoryName,tvPrice;
    public ImageView img;

    public CategoryViewHolder(@NonNull View itemView) {
        super(itemView);
        tvCategoryName = (TextView)itemView.findViewById(R.id.tvCategory);
        tvPrice = (TextView)itemView.findViewById(R.id.tvPrice);
        img = (ImageView)itemView.findViewById(R.id.imageView15);
    }

    @Override
    public void onClick(View v) {

    }
}
