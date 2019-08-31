package com.webhostapp.demo;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;


class programmingAdapter extends RecyclerView.Adapter<programmingAdapter.programmingHolder> {
    User[] data;
    Context context;
    images_layout abc= new images_layout();



    public programmingAdapter(Context context, User[] data) {
        this.context=context;
        this.data = data;
    }

    @NonNull
    @Override

    public programmingHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.images_layout, viewGroup, false);
         return new programmingHolder(view);
           }



    @Override
    public void onBindViewHolder(@NonNull programmingHolder programmingHolder, int i) {
       User some= data[i];
        Glide.with(programmingHolder.imageView.getContext()).load(some.getAvatarUrl()).into(programmingHolder.imageView);
programmingHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context,Offer_Activity.class);
        context.startActivity(intent);
    }
});
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class programmingHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
RelativeLayout relativeLayout;
        public programmingHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            relativeLayout=itemView.findViewById(R.id.relative);
          }
    }}