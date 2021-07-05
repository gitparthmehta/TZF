package com.tarezameen.foundation.Screens.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.tarezameen.foundation.R;
import com.tarezameen.foundation.Screens.Models.ImageList;

import java.util.ArrayList;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.MyViewHolder> {

    private ArrayList<ImageList> imageList;
    private Context context;

    public ImageAdapter(ArrayList<ImageList> imageList, Context context) {
        this.imageList = imageList;
        this.context = context;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView price, discount, rating, cuisine, restaurant, city, suburbname, EXTRA_DET;
        ImageView indigator_image, heart;
        RatingBar ratingBar;

        public MyViewHolder(View view) {
            super(view);

            indigator_image = (ImageView) view.findViewById(R.id.indigator_image);
        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.indigator_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Glide.with(context)
                .load(imageList.get(position).getImageurl())
                .fitCenter()
                .into(holder.indigator_image);
//        if (imageList.get(position).getImageurl().isEmpty()) {
//
//            Glide.with(context)
//                    .load(R.drawable.person)
//                    .fitCenter()
//                    .into(holder.indigator_image);
//
//        } else {
//
////            holder.indigator_image.setImageURI();
//
//            Glide.with(context)
//                    .load(imageList.get(position).getImageurl())
//                    .fitCenter()
//                    .into(holder.indigator_image);
//
//        }

    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }
}
