package com.webhostapp.demo;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;


import java.util.List;
import java.util.zip.Inflater;

public class Offer_Adapter extends RecyclerView.Adapter<Offer_Adapter.ProductViewHolder> {
    CardView cardView;
    public String name, image, area,gender,religion;
    public int age;
    private static List<Offer> offerList;
    private Context mCtx;

    public Offer_Adapter(Context mCtx, List<Offer> offerList) {
        this.mCtx = mCtx;
        this.offerList = offerList;
    }



    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View contactView = inflater.inflate(R.layout.test, parent, false);
        cardView = contactView.findViewById(R.id.cardview1111);
        return new ProductViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(final ProductViewHolder holder, final int position) {
        final Offer product = offerList.get(position);
        Toast.makeText(mCtx, ""+position, Toast.LENGTH_SHORT).show();
        //loading the image
        Glide.with(mCtx)
                .load(product.getImage())
                .into(holder.imageView);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 image = product.getImage();
                 mCtx.startActivity(new Intent(mCtx, Offer_Activity.class).putExtra("image", image));
            }
        });

    }

    @Override
    public int getItemCount() {
        return offerList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        public ProductViewHolder(final View itemView) {
            super(itemView);
             imageView = itemView.findViewById(R.id.offer_load_image);
        }

    }


}